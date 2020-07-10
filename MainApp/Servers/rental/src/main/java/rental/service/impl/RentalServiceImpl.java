package rental.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rental.dto.RentalDTO;
import rental.dto.RentalPageDTO;
import rental.exceptions.ConflictException;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.EntityNotFound;
import rental.model.Bundle;
import rental.model.Rental;
import rental.dto.RentalStatus;
import rental.mq.RentalMQSender;
import rental.repository.BundleRepository;
import rental.repository.RentalRepository;
import rental.service.RentalService;
import saga.commands.rentalReservedCommands.RentalReservedCommand;
import saga.dto.VehicleOccupancyDTO;

import javax.inject.Inject;
import java.util.*;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    BundleRepository bundleRepository;
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    RentalMQSender rentalMQSender;
    @Inject
    private transient CommandGateway commandGateway;

    @Override
    @Transactional
    public RentalDTO convertToDTO(Rental rental) throws ConversionFailedError {
        try {
            RentalDTO rentalDTO = mapper.map(rental, RentalDTO.class);
            if (rentalDTO.getBundle() != null) {
                rentalDTO.getBundle().setRentals(new HashSet<>());
            }
            return rentalDTO;
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Rental convertToModel(RentalDTO rentalDTO) throws ConversionFailedError {
        try {
            return mapper.map(rentalDTO, Rental.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public RentalDTO add(RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError {
        Rental newRental = convertToModel(rentalDTO);

        if (rentalDTO.getBundle() != null) {
            Optional<Bundle> bundle = bundleRepository.findById(rentalDTO.getBundle().getId());
            if (!bundle.isPresent()) {
                throw new EntityNotFound("Bundle is not found");
            }
            newRental.setBundle(bundle.get());
        }

        newRental.setStatus(RentalStatus.PENDING);
        Rental saved = rentalRepository.save(newRental);
        rentalMQSender.send(convertToDTO(saved));
        return convertToDTO(saved);
    }

    @Override
    @Transactional
    public RentalDTO addViaSOAP(RentalDTO rentalDTO) throws EntityNotFound, ConversionFailedError {
        Rental newRental = convertToModel(rentalDTO);

        if (rentalDTO.getBundle() != null) {
            Optional<Bundle> bundle = bundleRepository.findById(rentalDTO.getBundle().getId());
            if (!bundle.isPresent()) {
                throw new EntityNotFound("Bundle is not found");
            }
            newRental.setBundle(bundle.get());
        }

        newRental.setStatus(rentalDTO.getStatus());
        Rental saved = rentalRepository.save(newRental);
        return convertToDTO(saved);
    }


    @Override
    public RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO update(Long id, RentalDTO rentalDTO, boolean overSoap) throws EntityNotFound, ConversionFailedError, ConflictException {
        Optional<Rental> rental = rentalRepository.findById(id);

        if (!rental.isPresent())
            throw new EntityNotFound("Invalid rental id");

        if (rentalDTO.getStatus().equals(RentalStatus.RESERVED) &&
                rental.get().getStatus().equals(RentalStatus.CANCELED)) {
            throw new ConflictException("Rental request has already been canceled");
        }
        rental.get().setStatus(rentalDTO.getStatus());
        Rental saved = rentalRepository.save(rental.get());
        System.out.println(saved.getStatus());

        // Update on agent
        if (!overSoap && saved.getCid() != null) {
            sendOverMq(id, saved);
        }

        if (saved.getStatus().equals(RentalStatus.RESERVED)) {
            VehicleOccupancyDTO occupied = new VehicleOccupancyDTO();
            occupied.setStartTime(saved.getStartTime());
            occupied.setEndTime(saved.getEndTime());
            occupied.setType("RENTAL");
            this.rejectRentalsFromTo(saved.getVehicleId(), occupied, saved.getId());
            commandGateway.send(new RentalReservedCommand(saved.getId(), occupied, saved.getVehicleId()));
        } else if (saved.getStatus().equals(RentalStatus.CANCELED)) {
            this.rejectRentalsFromBundle(saved.getBundle());
        }

        return convertToDTO(saved);
    }

    private void rejectRentalsFromBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        List<Rental> rentals = rentalRepository.findAllByBundle(bundle);
        for (Rental rental: rentals) {
            if (rental.getStatus() != RentalStatus.CANCELED){
                rental.setStatus(RentalStatus.CANCELED);
                rentalRepository.save(rental);
            }
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFound {
        Optional<Rental> rental = rentalRepository.findById(id);
        if(!rental.isPresent()) {
            throw new EntityNotFound("Rental not found");
        }

        rental.get().setDeleted(true);
        rentalRepository.save(rental.get());
    }

    @Override
    public void rejectRentalsFromTo(Long vehicleId, VehicleOccupancyDTO occupancyDTO, Long excludeId) {
        List<Rental> rentals = rentalRepository.findByVehicleAndByStartAndEndTimeExceptSingleRental(vehicleId, occupancyDTO.getStartTime(), occupancyDTO.getEndTime(), excludeId);
        HashMap<Long, Bundle> bundles = new HashMap<>();
        for (Rental rental: rentals) {
            if (rental.getStatus() != RentalStatus.CANCELED){
                rental.setStatus(RentalStatus.CANCELED);
                rentalRepository.save(rental);
            }

            Bundle bundle = rental.getBundle();
            if (bundle != null) {
                bundles.put(bundle.getId(), bundle);
            }
        }

        for (Bundle bundle: bundles.values()){
            for (Rental rental: bundle.getRentals()){
                if (rental.getStatus() != RentalStatus.CANCELED){
                    rental.setStatus(RentalStatus.CANCELED);
                    rentalRepository.save(rental);
                }
            }
        }

//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("vehicleID", vehicleId);
//        messageProperties.setHeader("excludeID", excludeId);
//        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
//        messageProperties.setHeader("__TypeId__", "saga.dto.VehicleOccupancyDTO");
//        byte[] ModelDTOBytes = null;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            ModelDTOBytes = objectMapper.writeValueAsBytes(occupancyDTO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        Message message = new Message(ModelDTOBytes, messageProperties);
//        rentalMQSender.send(message);
    }

    @Override
    public RentalPageDTO getByCustomerAndByStatusPageable(Integer pageNo, String sortKey, Long customerId, String statusName) throws ConversionFailedError, EntityNotFound {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        RentalStatus status = RentalStatus.findByName(statusName);
        if (status == null) {
            throw new EntityNotFound("Invalid status");
        }
        Page<Rental> pagedResult = rentalRepository.findByCustomerIdAndStatus(customerId, status, page);

        RentalPageDTO pageDTO = new RentalPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Rental rental: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(rental));
        }

        return pageDTO;
    }

    @Override
    public RentalPageDTO getByOwnerAndByStatusPageable(Integer pageNo, String sortKey, Long customerId, String statusName) throws ConversionFailedError, EntityNotFound {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        RentalStatus status = RentalStatus.findByName(statusName);
        if (status == null) {
            throw new EntityNotFound("Invalid status");
        }
        Page<Rental> pagedResult = rentalRepository.findByOwnerIdAndStatus(customerId, status, page);

        RentalPageDTO pageDTO = new RentalPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Rental rental: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(rental));
        }

        return pageDTO;
    }

    @Override
    public void sagaRollback(Long rentalId) throws EntityNotFound {
        Optional<Rental> rental = rentalRepository.findById(rentalId);
        if(!rental.isPresent()) {
            throw new EntityNotFound("Rental not found");
        }

        rental.get().setStatus(RentalStatus.PENDING);
        rentalRepository.save(rental.get());
    }

    public void sendOverMq(Long id, Rental saved) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("rentalID", id);
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setHeader("__TypeId__", "rental.dto.RentalDTO");
        byte[] ModelDTOBytes = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ModelDTOBytes = objectMapper.writeValueAsBytes(convertToDTO(saved));
        } catch (JsonProcessingException | ConversionFailedError e) {
            e.printStackTrace();
        }
        Message message = new Message(ModelDTOBytes, messageProperties);
        rentalMQSender.send(message);
    }
}

package agent.service.rental.impl;

import agent.dto.rental.RentalDTO;
import agent.dto.rental.RentalPageDTO;
import agent.dto.shared.VehicleOccupancyDTO;
import agent.exceptions.ConflictException;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Bundle;
import agent.model.rental.Rental;
import agent.model.rental.RentalStatus;
import agent.model.rental.mappings.BundleMapping;
import agent.model.rental.mappings.RentalMapping;
import agent.model.user.User;
import agent.model.user.mappings.UserMapping;
import agent.model.vehicle.mappings.VehicleMapping;
import agent.repository.rental.BundleRepository;
import agent.repository.rental.RentalRepository;
import agent.repository.rental.mappingsRepo.BundleMappingRepo;
import agent.repository.rental.mappingsRepo.RentalMappingRepo;
import agent.repository.user.UserMappingRepo;
import agent.repository.user.UserRepository;
import agent.repository.vehicle.VehicleRepo;
import agent.repository.vehicle.mappingsRepo.VehicleMappingRepo;
import agent.service.rental.RentalService;
import agent.service.vehicle.VehicleOccupancyService;
import agent.soap.RentalClient;
import agent.soap.gen.BundleDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    BundleRepository bundleRepository;
    @Autowired
    VehicleOccupancyService vehicleOccupancyService;
    @Autowired
    RentalMappingRepo rentalMappingRepo;
    @Autowired
    BundleMappingRepo bundleMappingRepo;
    @Autowired
    VehicleRepo vehicleRepo;
    @Autowired
    VehicleMappingRepo vehicleMappingRepo;
    @Autowired
    UserMappingRepo userMappingRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    RentalClient rentalClient;
    @Override
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
    public RentalDTO add(RentalDTO rentalDTO, boolean overMq) throws EntityNotFound, ConversionFailedError {
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
        if (!overMq) {
            sendRentalViaSoap(saved);
        }
        return convertToDTO(saved);
    }

    @Override
    public RentalDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        return null;
    }

    @Override
    public RentalDTO update(Long id, RentalDTO rentalDTO, boolean overMq) throws EntityNotFound, ConversionFailedError, ConflictException, DuplicateEntity {
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

        if (!overMq) {
            agent.soap.gen.RentalDTO soapDTO = mapper.map(saved, agent.soap.gen.RentalDTO.class);
            // Only id and status is important when updating
            soapDTO.setId(rentalMappingRepo.findByRentalAgentId(saved).getRentalBackId());
            soapDTO.setCustomerId(null);
            rentalClient.updateRental(soapDTO);
        }

        if (saved.getStatus().equals(RentalStatus.RESERVED)) {
            VehicleOccupancyDTO occupied = new VehicleOccupancyDTO();
            occupied.setStartTime(saved.getStartTime());
            occupied.setEndTime(saved.getEndTime());
            occupied.setType("RENTAL");
            this.rejectRentalsFromTo(saved.getVehicleId(), occupied, saved.getId());
            vehicleOccupancyService.add(saved.getVehicleId(), occupied, false);
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

    public void updateViaMQ(Long id, RentalDTO rentalDTO){

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

    public void sendRentalViaSoap(Rental rental) {
        // Rental can be in the bundle or not
        Long backBundleId = null;
        Bundle rentalBundle = rental.getBundle();
        // If rental is in the bundle
        if(rentalBundle != null) {
            // Check if bundle was already saved during previous bundle processing
            BundleMapping bundleMapping = bundleMappingRepo.findByBundleAgent(rentalBundle);
            if(bundleMapping == null){
                // If not, save bundle in MS and map the id
                BundleDTO savedBundleDTO = rentalClient.addBundle(mapper.map(rentalBundle, BundleDTO.class)).getValue();
                if(savedBundleDTO != null) {
                    BundleMapping newBundleMapping = new BundleMapping();
                    newBundleMapping.setBundleAgent(rentalBundle);
                    newBundleMapping.setBundleBackId(savedBundleDTO.getId());
                    bundleMappingRepo.save(newBundleMapping);
                    backBundleId = savedBundleDTO.getId();
                }
            }else{
                backBundleId = bundleMapping.getBundleBackId();
            }
        }
        // Get the mappings for owner and vehicle
        agent.model.vehicle.Vehicle vehicle = vehicleRepo.findById(rental.getVehicleId()).get();
        VehicleMapping vehicleMapping = vehicleMappingRepo.findByVehicleAgentId(vehicle);
        User owner = userRepository.findById(rental.getOwnerId()).get();
        UserMapping ownerMapping = userMappingRepo.findByUserAgentId(owner);
        // Convert rental to DTO with mapped id's
        // Todo: mappings for rental report & (?) Customer (?)
        agent.soap.gen.RentalDTO rentalDTOtoSend = convertRentalToSOAPDTO(rental, vehicleMapping.getVehicleBackId(), backBundleId, ownerMapping.getUserBackId());
        // Send rental to MS and map the ID.
        rentalDTOtoSend.setCustomerId(null);
        agent.soap.gen.RentalDTO savedRentalDTO = rentalClient.addRental(rentalDTOtoSend).getValue();
        if(savedRentalDTO != null) {
            RentalMapping rentalMapping = new RentalMapping();
            rentalMapping.setRentalAgentId(rental);
            rentalMapping.setRentalBackId(savedRentalDTO.getId());
            rentalMappingRepo.save(rentalMapping);
        }
    }

    // Function to convert rental to DTO with mapped id's
    private agent.soap.gen.RentalDTO convertRentalToSOAPDTO(Rental rental, Long vehicleId, Long bundleId, Long ownerId){
        agent.soap.gen.RentalDTO retVal = mapper.map(rental, agent.soap.gen.RentalDTO.class);
        if (retVal.getBundle() != null) {
            retVal.getBundle().setId(bundleId);
        }
        retVal.setVehicleId(vehicleId);
        retVal.setOwnerId(ownerId);
        return retVal;
    }
}

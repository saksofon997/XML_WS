package vehicle.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainFuelCommand;
import saga.commands.vehiclePartsCommands.MainTransmissionCommand;
import saga.dto.ModelDTO;
import saga.dto.ReviewDTO;
import saga.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Fuel;
import vehicle.model.Model;
import vehicle.model.Review;
import vehicle.model.Transmission;
import vehicle.repository.TransmissionRepo;
import vehicle.repository.VehicleRepo;
import vehicle.service.TransmissionService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransmissionServiceImpl implements TransmissionService {

    @Autowired
    TransmissionRepo transmissionRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Inject
    private transient CommandGateway commandGateway;

    @Override
    public TransmissionDTO convertToDTO(Transmission transmission) throws ConversionFailedError {
        try {
            return mapper.map(transmission, TransmissionDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Transmission convertToModel(TransmissionDTO transmissionDTO) throws ConversionFailedError {
        try {
            return mapper.map(transmissionDTO, Transmission.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public List<TransmissionDTO> getAll() throws EntityNotFound, ConversionFailedError {

        List<Transmission> transmissions = transmissionRepo.findAll();

        if (transmissions.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<TransmissionDTO> transmissionDTOS = new ArrayList<>();

        for (Transmission t : transmissions) {
            transmissionDTOS.add(convertToDTO(t));
        }

        return transmissionDTOS;
    }

    @Override
    public TransmissionDTO add(TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity {

        Transmission newTransmission = convertToModel(transmissionDTO);

        if (!transmissionRepo.existsByName(transmissionDTO.getName())) {
            Transmission savedTransmission = transmissionRepo.save(newTransmission);
            commandGateway.send(new MainTransmissionCommand(savedTransmission.getId(), transmissionDTO, TypeOfCommand.CREATE));

        } else {
            throw new DuplicateEntity("Item with name: " + transmissionDTO.getName() + " already exists");
        }
        return transmissionDTO;
    }

    @Override
    public TransmissionDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> transmission = transmissionRepo.findById(id);

        if (!transmission.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }else {
            return convertToDTO(transmission.get());
        }
    }

    @Override
    public TransmissionDTO update(Long id, TransmissionDTO transmissionDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> change = transmissionRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        change.get().setName(transmissionDTO.getName());

        Transmission savedTransmission = transmissionRepo.save(change.get());
        transmissionDTO.setId(savedTransmission.getId());
        commandGateway.send(new MainTransmissionCommand(savedTransmission.getId(), transmissionDTO, TypeOfCommand.UPDATE));

        return transmissionDTO;
    }

    @Override
    public TransmissionDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Transmission> deleted = transmissionRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }else {
            deleted.get().setDeleted(true);
            transmissionRepo.save(deleted.get());
            //transmissionRepo.deleteById(id);
            commandGateway.send(new MainTransmissionCommand(deleted.get().getId(), convertToDTO(deleted.get()), TypeOfCommand.DELETE));

        }
        return convertToDTO(deleted.get());
    }
}

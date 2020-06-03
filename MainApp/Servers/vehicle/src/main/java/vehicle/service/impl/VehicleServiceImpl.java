package vehicle.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.commands.MainVehicleCommand;
import saga.commands.TypeOfCommand;
import saga.dto.VehicleDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Vehicle;
import vehicle.repository.VehicleRepo;
import vehicle.service.VehicleService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Inject
    private transient CommandGateway commandGateway;
    @Autowired
    VehicleRepo vehicleRepo;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError {
        try {
            return mapper.map(vehicle, VehicleDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError {
        try {
            return mapper.map(vehicleDTO, Vehicle.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public List<VehicleDTO> getAll() {
        return null;
    }

    @Override
    public VehicleDTO add(VehicleDTO vehicleDTO) throws ConversionFailedError, DuplicateEntity {

        Vehicle newBrand = convertToModel(vehicleDTO);

        Vehicle savedVehicle = vehicleRepo.save(newBrand);

        System.out.println(savedVehicle.getId());
        commandGateway.send(new MainVehicleCommand(savedVehicle.getId(),vehicleDTO, TypeOfCommand.CREATE));

        return vehicleDTO;
    }

    @Override
    public VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);

        if (!vehicle.isPresent()){
            throw new EntityNotFound("No item with ID: "+id);
        } else {
            return convertToDTO(vehicle.get());
        }

    }

    @Override
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> change = vehicleRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        }
        Vehicle updatedVehicle = convertToModel(vehicleDTO);
        updatedVehicle.setId(change.get().getId());

        Vehicle savedVehicle = vehicleRepo.save(updatedVehicle);
        vehicleDTO.setId(savedVehicle.getId());
        commandGateway.send(new MainVehicleCommand(savedVehicle.getId(), vehicleDTO, TypeOfCommand.UPDATE));
        return vehicleDTO;
    }

    @Override
    public VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> deleted = vehicleRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No item with ID: "+id);
        }
        Vehicle vehicle = deleted.get();
        vehicle.setDeleted(true);
        Vehicle deletedVehicle = vehicleRepo.save(vehicle);
        VehicleDTO deletedVehicleDTO = convertToDTO(deletedVehicle);
        commandGateway.send(new MainVehicleCommand(deletedVehicle.getId(),deletedVehicleDTO, TypeOfCommand.DELETE));
        return deletedVehicleDTO;
    }

    @Override
    public VehicleDTO deletePermanent (Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Vehicle> deleted = vehicleRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No item with ID: "+id);
        }
        vehicleRepo.deleteById(id);
        VehicleDTO deletedVehicleDTO = convertToDTO(deleted.get());
        commandGateway.send(new MainVehicleCommand(id,deletedVehicleDTO, TypeOfCommand.DELETE));
        return deletedVehicleDTO;
    }

}

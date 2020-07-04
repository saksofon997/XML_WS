package agent.service.vehicle;

import agent.dto.shared.VehicleDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.OperationNotAllowed;
import agent.model.vehicle.Vehicle;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VehicleService {

    VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError;

    Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError;

    List<VehicleDTO> getAll();

    List<VehicleDTO> getOwnersVehicles(Long id) throws ConversionFailedError;

    VehicleDTO add(VehicleDTO vehicleDTO, MultipartFile[] images, HttpServletRequest request, Boolean isAgent) throws ConversionFailedError, DuplicateEntity, OperationNotAllowed, EntityNotFound;

    VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError;

    VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError;

    Resource getImage(String path, HttpServletRequest request) throws IOException;

    void updateMileage(Long vehicleId, double mileage) throws EntityNotFound;

    agent.soap.gen.Vehicle updateVehicleSOAPParts(agent.soap.gen.Vehicle vehicleToUpdate) throws EntityNotFound;
}

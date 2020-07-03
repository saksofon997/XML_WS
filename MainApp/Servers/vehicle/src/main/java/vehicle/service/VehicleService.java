package vehicle.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import saga.dto.VehicleDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.OperationNotAllowed;
import vehicle.model.Vehicle;
import vehicle.soap.arrays.VehicleArray;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VehicleService {

    VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError;

    Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError;

    List<VehicleDTO> getAll();

    List<VehicleDTO> getOwnersVehicles(Long id) throws ConversionFailedError;

    VehicleDTO add(VehicleDTO vehicleDTO, MultipartFile[] images, HttpServletRequest request, Boolean isAgent) throws ConversionFailedError, DuplicateEntity, OperationNotAllowed;

    VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError;

    VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError;

    Resource getImage(String path, HttpServletRequest request) throws IOException;

    Long addViaSoap(Vehicle vehicle) throws ConversionFailedError;

    VehicleArray getAllSOAP();

    Vehicle getOneSOAP(Long id);

    public Vehicle updateOneSOAP(Long id, Vehicle vehicle);

    void updateMileage(Long vehicleId, double mileage) throws EntityNotFound, ConversionFailedError;
}

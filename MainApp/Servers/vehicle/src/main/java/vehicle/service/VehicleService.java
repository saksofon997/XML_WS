package vehicle.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import saga.dto.BrandDTO;
import saga.dto.VehicleDTO;
import vehicle.dto.FullVehicleDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Vehicle;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VehicleService {

    VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError;

    Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError;

    List<VehicleDTO> getAll();

    VehicleDTO add(VehicleDTO vehicleDTO, MultipartFile[] images, HttpServletRequest request) throws ConversionFailedError, DuplicateEntity;

    VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError;

    VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError;

    Resource getImage(String path, HttpServletRequest request) throws IOException;
}

package vehicle.service;

import saga.dto.BrandDTO;
import saga.dto.VehicleDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDTO convertToDTO(Vehicle vehicle) throws ConversionFailedError;

    Vehicle convertToModel(VehicleDTO vehicleDTO) throws ConversionFailedError;

    List<VehicleDTO> getAll();

    VehicleDTO add(VehicleDTO vehicleDTO) throws ConversionFailedError, DuplicateEntity;

    VehicleDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws EntityNotFound, ConversionFailedError;

    VehicleDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

package vehicle.service;

import saga.dto.VehicleOccupancyDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface VehicleOccupancyService {
    List<VehicleOccupancyDTO> getAll(Long vehicleId) throws EntityNotFound, ConversionFailedError;

    VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity;

    VehicleOccupancyDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;
}

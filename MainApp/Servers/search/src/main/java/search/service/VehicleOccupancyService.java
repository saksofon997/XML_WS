package search.service;

import saga.dto.VehicleOccupancyDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.VehicleOccupancy;

import java.util.List;

public interface VehicleOccupancyService {
    List<VehicleOccupancyDTO> getAll(Long vehicleId) throws EntityNotFound, ConversionFailedError;

    VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity;

    VehicleOccupancyDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;

    public VehicleOccupancy convertToModel(VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError;

    public VehicleOccupancyDTO convertToDTO(VehicleOccupancy vehicleOccupancy) throws ConversionFailedError;
}

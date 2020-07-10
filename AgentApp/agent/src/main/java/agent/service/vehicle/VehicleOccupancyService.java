package agent.service.vehicle;


import agent.dto.shared.VehicleOccupancyDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.VehicleOccupancy;

import java.util.List;

public interface VehicleOccupancyService {
    List<VehicleOccupancyDTO> getAll(Long vehicleId) throws EntityNotFound, ConversionFailedError;

    VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO, boolean overMq) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity;

    VehicleOccupancyDTO delete(Long vehicleId, Long id) throws EntityNotFound, ConversionFailedError;

    public VehicleOccupancy convertToModel(VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError;

    public VehicleOccupancyDTO convertToDTO(VehicleOccupancy vehicleOccupancy) throws ConversionFailedError;

    List<VehicleOccupancyDTO> getOccupanciesOfGivenPeriod(Long vehicleId, long start_time, long end_time) throws EntityNotFound, ConversionFailedError;
}

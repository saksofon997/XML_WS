package search.service;

import saga.dto.VehicleOccupancyDTO;

import java.util.List;

public interface VehicleOccupancyService {
    List<VehicleOccupancyDTO> getAll(Long vehicleId);

    VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO);

    VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO);

    VehicleOccupancyDTO delete(Long vehicleId, Long id);
}

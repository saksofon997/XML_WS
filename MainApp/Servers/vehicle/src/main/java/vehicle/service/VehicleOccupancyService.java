package vehicle.service;

import vehicle.dto.VehicleOccupancyDTO;

import java.util.List;

public interface VehicleOccupancyService {
    List<VehicleOccupancyDTO> getAll(Long vehicleId);

    VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO);

    VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO);

    VehicleOccupancyDTO delete(Long vehicleId, Long id);
}

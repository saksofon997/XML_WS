package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.VehicleOccupancyDTO;
import vehicle.repository.VehicleOccupancyRepo;
import vehicle.service.VehicleOccupancyService;

import java.util.List;

@Service
public class VehicleOccupancyServiceImpl implements VehicleOccupancyService {

    @Autowired
    VehicleOccupancyRepo vehicleOccupancyRepo;

    @Override
    public List<VehicleOccupancyDTO> getAll(Long vehicleId) {
        return null;
    }

    @Override
    public VehicleOccupancyDTO add(Long vehicleId, VehicleOccupancyDTO vehicleOccupancyDTO) {
        return null;
    }

    @Override
    public VehicleOccupancyDTO update(Long vehicleId, Long id, VehicleOccupancyDTO vehicleOccupancyDTO) {
        return null;
    }

    @Override
    public VehicleOccupancyDTO delete(Long vehicleId, Long id) {
        return null;
    }
}

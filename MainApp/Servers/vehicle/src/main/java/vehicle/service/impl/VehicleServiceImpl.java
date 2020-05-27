package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.VehicleDTO;
import vehicle.repository.VehicleRepo;
import vehicle.service.VehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public List<VehicleDTO> getAll() {
        return null;
    }

    @Override
    public VehicleDTO add(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public VehicleDTO getOne(Long id) {
        return null;
    }

    @Override
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public VehicleDTO delete(Long id) {
        return null;
    }
}

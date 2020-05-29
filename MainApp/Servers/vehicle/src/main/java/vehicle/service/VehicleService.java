package vehicle.service;

import vehicle.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAll();

    VehicleDTO add(VehicleDTO vehicleDTO);

    VehicleDTO getOne(Long id);

    VehicleDTO update(Long id, VehicleDTO vehicleDTO);

    VehicleDTO delete(Long id);
}

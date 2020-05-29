package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.FuelDTO;
import vehicle.repository.FuelRepo;
import vehicle.service.FuelService;

import java.util.List;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    FuelRepo fuelRepo;

    @Override
    public List<FuelDTO> getAll() {
        return null;
    }

    @Override
    public FuelDTO add(FuelDTO fuelDTO) {
        return null;
    }

    @Override
    public FuelDTO getOne(Long id) {
        return null;
    }

    @Override
    public FuelDTO update(Long id, FuelDTO fuelDTO) {
        return null;
    }

    @Override
    public FuelDTO delete(Long id) {
        return null;
    }
}

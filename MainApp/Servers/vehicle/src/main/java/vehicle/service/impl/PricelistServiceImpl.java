package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.PricelistDTO;
import vehicle.repository.PricelistRepo;
import vehicle.service.PricelistService;

import java.util.List;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    PricelistRepo pricelistRepo;

    @Override
    public PricelistDTO add(PricelistDTO pricelistDTO) {
        return null;
    }

    @Override
    public PricelistDTO getOne(Long id) {
        return null;
    }

    @Override
    public PricelistDTO update(Long id, Long pricelistDTO) {
        return null;
    }

    @Override
    public PricelistDTO delete(Long id) {
        return null;
    }

    @Override
    public List<PricelistDTO> getByOwner(Long ownerId) {
        return null;
    }
}

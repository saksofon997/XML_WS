package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.repository.PricelistRepo;
import vehicle.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    PricelistRepo pricelistRepo;
}

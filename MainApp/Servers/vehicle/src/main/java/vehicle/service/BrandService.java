package vehicle.service;

import org.springframework.stereotype.Service;
import vehicle.exceptions.ItemNotFound;
import vehicle.utils.ErrorMessages;

@Service
public class BrandService {

    public void getAll() throws ItemNotFound {
        throw new ItemNotFound(ErrorMessages.ITEM_NOT_FOUND());
    }
}

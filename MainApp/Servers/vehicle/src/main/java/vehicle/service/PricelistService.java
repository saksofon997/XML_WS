package vehicle.service;

import saga.dto.PricelistDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface PricelistService {
    PricelistDTO add(PricelistDTO pricelistDTO) throws ConversionFailedError, DuplicateEntity;

    PricelistDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    PricelistDTO update(Long id, PricelistDTO pricelistDTO) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    PricelistDTO delete(Long id) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    List<PricelistDTO> getByOwner(Long ownerId) throws EntityNotFound, ConversionFailedError;
}

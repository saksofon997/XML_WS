package agent.service.vehicle;


import agent.dto.shared.PricelistDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Pricelist;

import java.util.List;

public interface PricelistService {
    PricelistDTO add(PricelistDTO pricelistDTO, Boolean isAgent) throws ConversionFailedError, DuplicateEntity;

    PricelistDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    PricelistDTO update(Long id, PricelistDTO pricelistDTO) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    PricelistDTO delete(Long id) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    List<PricelistDTO> getByOwner(Long ownerId) throws EntityNotFound, ConversionFailedError;

    public PricelistDTO convertToDTO(Pricelist pricelist) throws ConversionFailedError;

    public Pricelist convertToModel(PricelistDTO pricelistDTO) throws ConversionFailedError;
}

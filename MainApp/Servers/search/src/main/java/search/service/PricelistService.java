package search.service;

import saga.dto.PricelistDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Pricelist;

import java.util.List;

public interface PricelistService {
    PricelistDTO add(PricelistDTO pricelistDTO) throws ConversionFailedError, DuplicateEntity;

    PricelistDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    PricelistDTO update(Long id, PricelistDTO pricelistDTO) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    PricelistDTO delete(Long id) throws EntityNotFound, DuplicateEntity, ConversionFailedError;

    List<PricelistDTO> getByOwner(Long ownerId) throws EntityNotFound, ConversionFailedError;

    public PricelistDTO convertToDTO(Pricelist pricelist) throws ConversionFailedError;

    public Pricelist convertToModel(PricelistDTO pricelistDTO) throws ConversionFailedError;
}

package search.service;

import saga.dto.BrandDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.exceptions.UnexpectedError;
import search.model.Brand;

import java.util.List;

public interface BrandService {

    BrandDTO convertToDTO(Brand brand) throws ConversionFailedError;

    Brand convertToModel(BrandDTO brandDTO) throws ConversionFailedError;

    BrandDTO add(BrandDTO brandDTO) throws DuplicateEntity, ConversionFailedError;

    BrandDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    List<BrandDTO> getAll() throws EntityNotFound;

    BrandDTO update(Long id, BrandDTO brandDTO) throws EntityNotFound, ConversionFailedError;

    BrandDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

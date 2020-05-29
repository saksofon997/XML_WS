package vehicle.service;

import vehicle.dto.BrandDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.UnexpectedError;
import vehicle.model.Brand;

import java.util.List;

public interface BrandService {

    BrandDTO convertToDTO(Brand brand) throws ConversionFailedError;

    Brand convertToModel(BrandDTO brandDTO) throws ConversionFailedError;

    BrandDTO add(BrandDTO brandDTO) throws DuplicateEntity;

    BrandDTO getOne(Long id) throws EntityNotFound;

    List<BrandDTO> getAll() throws EntityNotFound;

    BrandDTO update(Long id, BrandDTO brandDTO) throws UnexpectedError;

    BrandDTO delete(Long id) throws EntityNotFound;
}

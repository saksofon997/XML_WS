package vehicle.service;

import saga.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.UnexpectedError;
import vehicle.model.Brand;
import vehicle.soap.arrays.BrandArray;

import java.util.List;

public interface BrandService {

    BrandDTO convertToDTO(Brand brand) throws ConversionFailedError;

    Brand convertToModel(BrandDTO brandDTO) throws ConversionFailedError;

    BrandDTO add(BrandDTO brandDTO) throws DuplicateEntity, ConversionFailedError;

    BrandDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    BrandPageDTO getAllPageable(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<BrandDTO> getAll() throws ConversionFailedError;

    BrandDTO update(Long id, BrandDTO brandDTO) throws UnexpectedError, EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;

    BrandDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError;

    BrandArray getAllSOAP();
}

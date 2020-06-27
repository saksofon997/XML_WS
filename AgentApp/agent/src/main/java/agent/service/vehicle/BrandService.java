package agent.service.vehicle;


import agent.dto.shared.BrandDTO;
import agent.dto.vehicle.BrandPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.UnexpectedError;
import agent.model.vehicle.Brand;

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

    void addBrandViaMQ(saga.dto.BrandDTO brandDTO);
}

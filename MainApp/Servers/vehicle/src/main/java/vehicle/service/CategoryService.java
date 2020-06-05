package vehicle.service;

import saga.dto.CategoryDTO;
import vehicle.dto.CategoryPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface CategoryService {
    CategoryPageDTO getAllPageable(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<CategoryDTO> getAll() throws ConversionFailedError;

    CategoryDTO add(CategoryDTO categoryDTO) throws ConversionFailedError, DuplicateEntity;

    CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;
}

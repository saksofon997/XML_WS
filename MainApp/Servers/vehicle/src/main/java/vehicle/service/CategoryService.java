package vehicle.service;

import vehicle.dto.CategoryDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll() throws EntityNotFound, ConversionFailedError;

    CategoryDTO add(CategoryDTO categoryDTO) throws ConversionFailedError, DuplicateEntity;

    CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound;

    CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

package vehicle.service;

import vehicle.dto.BrandDTO;
import vehicle.dto.CategoryDTO;
import vehicle.dto.CategoryPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO convertToDTO(Category category) throws ConversionFailedError;

    Category convertToModel(CategoryDTO categoryDTO) throws ConversionFailedError;

    CategoryPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError;

    CategoryDTO add(CategoryDTO categoryDTO) throws DuplicateEntity, ConversionFailedError;

    CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError;

    CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

package search.service;

import saga.dto.CategoryDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();

    CategoryDTO convertToDTO(Category category) throws ConversionFailedError;

    Category convertToModel(CategoryDTO categoryDTO) throws ConversionFailedError;

    CategoryDTO add(CategoryDTO categoryDTO) throws DuplicateEntity, ConversionFailedError;

    CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError;

    CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

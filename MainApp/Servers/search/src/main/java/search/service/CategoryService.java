package search.service;

import search.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();

    CategoryDTO add(CategoryDTO categoryDTO);

    CategoryDTO getOne(Long id);

    CategoryDTO update(Long id, CategoryDTO categoryDTO);

    CategoryDTO delete(Long id);
}

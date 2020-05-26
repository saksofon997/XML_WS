package vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.CategoryDTO;
import vehicle.repository.CategoryRepo;
import vehicle.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<CategoryDTO> getAll() {
        return null;
    }

    @Override
    public CategoryDTO add(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO getOne(Long id) {
        return null;
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO delete(Long id) {
        return null;
    }
}

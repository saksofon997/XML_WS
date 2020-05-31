package search.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.CategoryDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Category;
import search.repository.CategoryRepo;
import search.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDTO convertToDTO(Category category) throws ConversionFailedError {
        return new DozerBeanMapper().map(category, CategoryDTO.class);
    }

    @Override
    public Category convertToModel(CategoryDTO categoryDTO) throws ConversionFailedError {
        return new DozerBeanMapper().map(categoryDTO, Category.class);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return null;
    }

    @Override
    public CategoryDTO add(CategoryDTO categoryDTO) throws DuplicateEntity, ConversionFailedError {
        Category categoryToCheck = categoryRepo.findByName(categoryDTO.getName());
        if (categoryToCheck != null){
            throw new DuplicateEntity("Category with this name already exists");
        }
        Category category = convertToModel(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()){
            return convertToDTO(category.get());
        } else {
            throw new EntityNotFound("Category with this id not found.");
        }
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Category> check = categoryRepo.findById(id);
        if (!check.isPresent() || !id.equals(categoryDTO.getId())){
            throw new EntityNotFound("Category not found, invalid data");
        }
        Category category = check.get();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepo.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Category> check = categoryRepo.findById(id);
        if (!check.isPresent() || !id.equals(id)){
            throw new EntityNotFound("Brand not found, invalid data");
        }
        Category category = check.get();
        category.setDeleted(true);
        return convertToDTO(categoryRepo.save(category));
    }
}

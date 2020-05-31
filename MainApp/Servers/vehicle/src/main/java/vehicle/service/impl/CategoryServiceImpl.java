package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vehicle.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.dto.CategoryDTO;
import vehicle.dto.CategoryPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Category;
import vehicle.model.Model;
import vehicle.repository.CategoryRepo;
import vehicle.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public CategoryPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError{
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        Page<Category> pagedResult = categoryRepo.findAll(page);

        CategoryPageDTO pageDTO = new CategoryPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Category category: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(category));
        }

        return pageDTO;
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

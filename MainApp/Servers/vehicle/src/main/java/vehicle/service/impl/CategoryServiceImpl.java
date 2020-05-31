package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import saga.dto.BrandDTO;
import vehicle.dto.CategoryDTO;
import vehicle.dto.CategoryPageDTO;
import saga.dto.ModelDTO;
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

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    DozerBeanMapper mapper;

    public CategoryDTO convertToDTO(Category category) throws ConversionFailedError {
        try {
            return mapper.map(category, CategoryDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public Category convertToModel(CategoryDTO categoryDTO) throws ConversionFailedError {
        try {
            return mapper.map(categoryDTO, Category.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
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
    public CategoryDTO add(CategoryDTO categoryDTO) throws ConversionFailedError, DuplicateEntity {

        Category newCat = convertToModel(categoryDTO);

        if (!categoryRepo.existsByName(categoryDTO.getName()))
            categoryRepo.save(newCat);
        else
            throw new DuplicateEntity("Item with name: "+categoryDTO.getName()+" already exists");

        return categoryDTO;
    }

    @Override
    public CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Category> category = categoryRepo.findById(id);

        if (!category.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            return convertToDTO(category.get());
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound {

        Optional<Category> change = categoryRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(categoryDTO.getName());

        categoryRepo.save(change.get());

        return categoryDTO;
    }

    @Override
    public CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Category> deleted = categoryRepo.findById(id);

        if (!deleted.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            categoryRepo.deleteById(id);

        return convertToDTO(deleted.get());
    }
}

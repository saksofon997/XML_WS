package vehicle.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import saga.commands.MainBrandCommand;
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainCategoryCommand;
import saga.dto.BrandDTO;
import saga.dto.CategoryDTO;
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

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Inject
    private transient CommandGateway commandGateway;

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

        if (!categoryRepo.existsByName(categoryDTO.getName())) {
            Category savedCategory = categoryRepo.save(newCat);
            commandGateway.send(new MainCategoryCommand(savedCategory.getId(), categoryDTO, TypeOfCommand.CREATE));
        } else {
            throw new DuplicateEntity("Category with name: " + categoryDTO.getName() + " already exists");
        }
        return categoryDTO;
    }

    @Override
    public CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Category> category = categoryRepo.findById(id);

        if (!category.isPresent()) {
            throw new EntityNotFound("No category with ID: " + id);
        } else {
            return convertToDTO(category.get());
        }
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound {

        Optional<Category> change = categoryRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No category with ID: " + id);
        }
        change.get().setName(categoryDTO.getName());

        Category  savedCategory = categoryRepo.save(change.get());

        commandGateway.send(new MainCategoryCommand(savedCategory.getId(), categoryDTO, TypeOfCommand.UPDATE));

        return categoryDTO;
    }

    @Override
    public CategoryDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Category> deleted = categoryRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No category with ID: " + id);
        } else {
            categoryRepo.deleteById(id);
            commandGateway.send(new MainCategoryCommand(deleted.get().getId(), convertToDTO(deleted.get()), TypeOfCommand.DELETE));
        }

        return convertToDTO(deleted.get());
    }
}

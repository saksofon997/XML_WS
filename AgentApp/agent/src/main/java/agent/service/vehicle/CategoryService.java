package agent.service.vehicle;

import agent.dto.shared.CategoryDTO;
import agent.dto.vehicle.CategoryPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;

import java.util.List;

public interface CategoryService {
    CategoryPageDTO getAllPageable(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<CategoryDTO> getAll() throws ConversionFailedError;

    CategoryDTO add(CategoryDTO categoryDTO) throws ConversionFailedError, DuplicateEntity;

    CategoryDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    CategoryDTO update(Long id, CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;
}

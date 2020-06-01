package search.service;

import saga.dto.ModelDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Model;

import java.util.List;

public interface ModelService {
    List<ModelDTO> getAll(Long brandId);
    ModelDTO add(Long brandId, ModelDTO modelDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    ModelDTO getOne(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound;

    ModelDTO delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    Model convertToModel(ModelDTO m) throws ConversionFailedError;
    ModelDTO convertToDTO(Model model) throws ConversionFailedError;
}

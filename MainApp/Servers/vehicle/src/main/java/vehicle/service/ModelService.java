package vehicle.service;

import saga.dto.ModelDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Model;

import java.util.List;

public interface ModelService {
    List<ModelDTO> getAll(Long brandId) throws EntityNotFound, ConversionFailedError;

    ModelDTO add(Long brandId, ModelDTO modelDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    ModelDTO getOne(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound;

    ModelDTO delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    Model convertToModel(ModelDTO m) throws ConversionFailedError;
}

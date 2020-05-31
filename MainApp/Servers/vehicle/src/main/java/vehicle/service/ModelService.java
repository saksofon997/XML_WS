package vehicle.service;

import vehicle.dto.FuelDTO;
import vehicle.dto.ModelDTO;
import vehicle.dto.ModelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Fuel;
import vehicle.model.Model;

import java.util.List;

public interface ModelService {
    ModelDTO convertToDTO(Model model) throws ConversionFailedError;

    Model convertToModel(ModelDTO modelDTO) throws ConversionFailedError;

    List<ModelDTO> getAll(Long brandId) throws EntityNotFound, ConversionFailedError;

    ModelDTO add(Long brandId, ModelDTO modelDTO) throws DuplicateEntity, ConversionFailedError;

    ModelDTO getOne(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound, ConversionFailedError;

    ModelDTO delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;
}

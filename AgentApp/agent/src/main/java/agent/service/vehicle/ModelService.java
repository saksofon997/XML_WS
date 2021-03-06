package agent.service.vehicle;


import agent.dto.shared.ModelDTO;
import agent.dto.vehicle.ModelPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Model;

import java.util.List;

public interface ModelService {

    ModelPageDTO getAllPageable(Long brandId, Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<ModelDTO> getAll(Long brandId) throws EntityNotFound, ConversionFailedError;

    ModelDTO add(Long brandId, ModelDTO modelDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound;

    ModelDTO getOne(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound, ConversionFailedError;

    void delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError;

    Model convertToModel(ModelDTO m) throws ConversionFailedError;

    ModelDTO convertToDTO(Model model) throws ConversionFailedError;

    ModelDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError;

    void addModelViaMQ(saga.dto.ModelDTO modelDTO, Long brandId);
}

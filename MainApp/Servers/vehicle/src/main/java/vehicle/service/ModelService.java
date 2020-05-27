package vehicle.service;

import vehicle.dto.ModelDTO;

import java.util.List;

public interface ModelService {
    List<ModelDTO> getAll(Long brandId);

    ModelDTO add(Long brandId, ModelDTO modelDTO);

    ModelDTO getOne(Long brandId, Long id);

    ModelDTO update(Long brandId, Long id, ModelDTO modelDTO);

    ModelDTO delete(Long brandId, Long id);
}

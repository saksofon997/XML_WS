package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dto.ModelDTO;
import search.repository.ModelRepo;
import search.service.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepo modelRepo;

    @Override
    public List<ModelDTO> getAll(Long brandId) {
        return null;
    }

    @Override
    public ModelDTO add(Long brandId, ModelDTO modelDTO) {
        return null;
    }

    @Override
    public ModelDTO getOne(Long brandId, Long id) {
        return null;
    }

    @Override
    public ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) {
        return null;
    }

    @Override
    public ModelDTO delete(Long brandId, Long id) {
        return null;
    }
}

package search.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.ModelDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Brand;
import search.model.Model;
import search.repository.BrandRepo;
import search.repository.ModelRepo;
import search.service.ModelService;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public List<ModelDTO> getAll(Long brandId) {
        return null;
    }

    public ModelDTO convertToDTO(Model model) throws ConversionFailedError {
        try {
            return mapper.map(model, ModelDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Model convertToModel(ModelDTO modelDTO) throws ConversionFailedError {
        try {
            return mapper.map(modelDTO, Model.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public ModelDTO add(Long brandId, ModelDTO modelDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity {

        Model newModel = convertToModel(modelDTO);

        Optional<Brand> brand = brandRepo.findById(brandId);

        if (!brand.isPresent()) {
            throw new EntityNotFound("No item with ID: "+brandId);
        }

        if (brand.get().getModels().stream().anyMatch(m -> m.getName().equals(newModel.getName()))) {
            throw new DuplicateEntity("Item with name: "+modelDTO.getName()+" already exists");
        }
        brand.get().getModels().add(newModel);
        brandRepo.save(brand.get());
        modelRepo.save(newModel);

        return modelDTO;
    }

    @Override
    public ModelDTO getOne(Long brandId, Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Model> model = modelRepo.findById(id);

        if (!model.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        } else {
            return convertToDTO(model.get());
        }
    }

    @Override
    public ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound {

        Optional<Model> change = modelRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(modelDTO.getName());

        modelRepo.save(change.get());

        return modelDTO;
    }

    @Override
    public ModelDTO delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Model> deleted = modelRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No item with ID: " + id);
        } else {
            deleted.get().setDeleted(true);
            modelRepo.save(deleted.get());
        }
        return convertToDTO(deleted.get());
    }
}

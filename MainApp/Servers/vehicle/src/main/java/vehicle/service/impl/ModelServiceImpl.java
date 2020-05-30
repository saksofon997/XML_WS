package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.dto.ModelDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Model;
import vehicle.repository.BrandRepo;
import vehicle.repository.ModelRepo;
import vehicle.service.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    DozerBeanMapper mapper;

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
    public List<ModelDTO> getAll(Long brandId) throws EntityNotFound, ConversionFailedError {

        List<Model> models = modelRepo.findAll();

        if (models.isEmpty()) {
            throw new EntityNotFound("Items not found");
        }

        List<ModelDTO> modelDTOS = new ArrayList<>();

        for (Model m : models) {
            modelDTOS.add(convertToDTO(m));
        }

        return modelDTOS;
    }

    @Override
    public ModelDTO add(Long brandId, ModelDTO modelDTO) throws ConversionFailedError, DuplicateEntity, EntityNotFound {

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

        if (!model.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            return convertToDTO(model.get());
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

        if (!deleted.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            modelRepo.deleteById(id);

        return convertToDTO(deleted.get());
    }
}

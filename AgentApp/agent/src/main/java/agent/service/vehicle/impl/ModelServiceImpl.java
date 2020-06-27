package agent.service.vehicle.impl;

import agent.dto.shared.ModelDTO;
import agent.dto.vehicle.ModelPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Brand;
import agent.model.vehicle.Model;
import agent.model.vehicle.mappings.BrandMapping;
import agent.model.vehicle.mappings.ModelMapping;
import agent.repository.vehicle.BrandRepo;
import agent.repository.vehicle.ModelRepo;
import agent.repository.vehicle.mappingsRepo.ModelMappingRepo;
import agent.service.vehicle.ModelService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    ModelMappingRepo modelMappingRepo;

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
    public ModelPageDTO getAllPageable(Long brandId, Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> brand = brandRepo.findById(brandId);

        if (!brand.isPresent()) {
            throw new EntityNotFound("No item with ID: "+brandId);
        }

        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        Page<Model> pagedResult = modelRepo.findByBrand(brand.get(), page);

        ModelPageDTO pageDTO = new ModelPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Model model: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(model));
        }

        return pageDTO;
    }

    @Override
    public List<ModelDTO> getAll(Long brandId) throws ConversionFailedError, EntityNotFound {
        Optional<Brand> brand = brandRepo.findById(brandId);
        if (!brand.isPresent()) {
            throw new EntityNotFound("No item with ID: "+brandId);
        }

        List<Model> models = brand.get().getModels();
        List<ModelDTO> modelDTOS = new ArrayList<>();
        for (Model model: models){
            modelDTOS.add(convertToDTO(model));
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

        newModel.setBrand(brand.get());
        Model savedModel = modelRepo.save(newModel);

        return convertToDTO(savedModel);
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
    public ModelDTO update(Long brandId, Long id, ModelDTO modelDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Model> change = modelRepo.findById(id);

        Optional<Brand> brand = brandRepo.findById(brandId);

        if (!change.isPresent() || !brand.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(modelDTO.getName());

        Model savedModel = modelRepo.save(change.get());

        return modelDTO;
    }

    @Override
    @Transactional
    public void delete(Long brandId, Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Model> deleted = modelRepo.findById(id);

        Optional<Brand> changed = brandRepo.findById(brandId);

        if (!deleted.isPresent() ||!changed.isPresent()) {
            throw new EntityNotFound("No item with ID: " + id);
        } else {
            deleted.get().setDeleted(true);
            modelRepo.save(deleted.get());
        }
    }

    // For saga rollback
    @Override
    public ModelDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Model> deleted = modelRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No item with ID: " + id);
        } else {
            modelRepo.deleteById(id);
        }
        return convertToDTO(deleted.get());
    }

    @Override
    public void addModelViaMQ(saga.dto.ModelDTO modelDTO, Long brandId){


        Optional<Brand> brand = brandRepo.findById(brandId);

        if (!brand.isPresent()) {
            System.out.println("No item with ID: " + brandId);
            return;
        }

        if (brand.get().getModels().stream().anyMatch(m -> m.getName().equals(modelDTO.getName()))) {
            System.out.println("Item with name: "+modelDTO.getName()+" already exists");
            return;
        }

        Model newModel = modelRepo.findByName(modelDTO.getName());
        if (newModel == null) {
            newModel = mapper.map(modelDTO, Model.class);
            newModel.setBrand(brand.get());
            newModel.setId(null);
            newModel = modelRepo.save(newModel);
        }
        ModelMapping mm = modelMappingRepo.findByModelAgent(newModel);
        if (mm == null) {
            mm = new ModelMapping();
            mm.setModelAgent(newModel);
            mm.setModelBackId(newModel.getId());
            modelMappingRepo.save(mm);
        }
    }
}

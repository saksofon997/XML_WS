package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vehicle.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.dto.ModelDTO;
import vehicle.dto.ModelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Fuel;
import vehicle.model.Model;
import vehicle.repository.BrandRepo;
import vehicle.repository.ModelRepo;
import vehicle.service.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepo modelRepo;
    @Autowired
    BrandRepo brandRepo;
    @Override
    public ModelDTO convertToDTO(Model model) throws ConversionFailedError {
        return new DozerBeanMapper().map(model, ModelDTO.class);
    }

    @Override
    public Model convertToModel(ModelDTO modelDTO) throws ConversionFailedError {
        return new DozerBeanMapper().map(modelDTO, Model.class);
    }

    @Override
    public List<ModelDTO> getAll(Long brandId) throws EntityNotFound, ConversionFailedError {
        Optional<Brand> brand = brandRepo.findById(brandId);
        if (!brand.isPresent()){
            throw new EntityNotFound("Brand with this id not found.");
        }
        List<Model> result = modelRepo.getAllByBrand(brand.get());
        List<ModelDTO> modelDTOS = new ArrayList<ModelDTO>();
        for (Model model: result){
            modelDTOS.add(convertToDTO(model));
        }
        return modelDTOS;
    }

    @Override
    public ModelDTO add(Long brandId, ModelDTO modelDTO) throws DuplicateEntity, ConversionFailedError {
        Model modelToCheck = modelRepo.findByName(modelDTO.getName());
        if (modelToCheck != null){
            throw new DuplicateEntity("Model with this name already exists");
        }
        Model model = convertToModel(modelDTO);
        Model savedModel = modelRepo.save(model);
        return convertToDTO(savedModel);
    }

    @Override
    public ModelDTO getOne(Long brandId, Long id)  throws EntityNotFound, ConversionFailedError {
        Optional<Model> model = modelRepo.findById(id);
        if (model.isPresent()){
            return convertToDTO(model.get());
        } else {
            throw new EntityNotFound("Model with this id not found.");
        }
    }

    @Override
    public ModelDTO update(Long brandId, Long id, ModelDTO modelDTO)  throws EntityNotFound, ConversionFailedError {
        Optional<Model> check = modelRepo.findById(id);
        if (!check.isPresent() || !id.equals(modelDTO.getId())){
            throw new EntityNotFound("Model not found, invalid data");
        }
        Model model = check.get();
        model.setName(modelDTO.getName());
        Model savedModel = modelRepo.save(model);
        return convertToDTO(savedModel);
    }

    @Override
    public ModelDTO delete(Long brandId, Long id)  throws EntityNotFound, ConversionFailedError {
        Optional<Model> check = modelRepo.findById(id);
        if (!check.isPresent() || !id.equals(id)){
            throw new EntityNotFound("Model not found, invalid data");
        }
        Model model = check.get();
        model.setDeleted(true);
        return convertToDTO(modelRepo.save(model));
    }
}

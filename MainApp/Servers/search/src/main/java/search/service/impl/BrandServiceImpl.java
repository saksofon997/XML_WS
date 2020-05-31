package search.service.impl;

import com.netflix.discovery.converters.Auto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.BrandDTO;
import saga.dto.ModelDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.exceptions.UnexpectedError;
import search.model.Brand;
import search.model.Model;
import search.repository.BrandRepo;
import search.service.BrandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepo brandRepo;

    @Override
    public BrandDTO convertToDTO(Brand brand) throws ConversionFailedError {
        return new DozerBeanMapper().map(brand, BrandDTO.class);
    }

    @Override
    public Brand convertToModel(BrandDTO brandDTO) throws ConversionFailedError {
        return new DozerBeanMapper().map(brandDTO, Brand.class);
    }
    public Model convertModelDTOToEntity(ModelDTO modelDTO) {
        return new DozerBeanMapper().map(modelDTO, Model.class);
    }
    @Override
    public BrandDTO add(BrandDTO brandDTO) throws DuplicateEntity, ConversionFailedError {
        Brand brandToCheck = brandRepo.findByName(brandDTO.getName());
        if (brandToCheck != null){
            throw new DuplicateEntity("Brand with this name already exists");
        }
        Brand brand = convertToModel(brandDTO);
        Brand savedBrand = brandRepo.save(brand);
        return convertToDTO(savedBrand);
    }

    @Override
    public BrandDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Brand> brand = brandRepo.findById(id);
        if (brand.isPresent()){
            return convertToDTO(brand.get());
        } else {
            throw new EntityNotFound("Brand with this id not found.");
        }
    }

    @Override
    public List<BrandDTO> getAll() throws EntityNotFound {
        return null;
    }

    @Override
    public BrandDTO update(Long id, BrandDTO brandDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Brand> check = brandRepo.findById(id);
        if (!check.isPresent() || !id.equals(brandDTO.getId())){
            throw new EntityNotFound("Brand not found, invalid data");
        }
        Brand brand = check.get();
        brand.setName(brandDTO.getName());
        List<Model> models = brandDTO.getModels().stream()
                .map(this::convertModelDTOToEntity)
                .collect(Collectors.toList());
        brand.setModels((ArrayList<Model>) models);
        Brand savedBrand = brandRepo.save(brand);
        return convertToDTO(savedBrand);
    }

    @Override
    public BrandDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Brand> check = brandRepo.findById(id);
        if (!check.isPresent() || !id.equals(id)){
            throw new EntityNotFound("Brand not found, invalid data");
        }
        Brand brand = check.get();
        brand.setDeleted(true);
        return convertToDTO(brandRepo.save(brand));
    }
}

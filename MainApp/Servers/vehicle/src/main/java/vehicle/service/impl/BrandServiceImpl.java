package vehicle.service.impl;

import com.netflix.discovery.converters.Auto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vehicle.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.dto.ModelDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.UnexpectedError;
import vehicle.model.Brand;
import vehicle.model.Model;
import vehicle.repository.BrandRepo;
import vehicle.service.BrandService;

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
    public BrandPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        Page<Brand> pagedResult = brandRepo.findAll(page);

        BrandPageDTO pageDTO = new BrandPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Brand brand: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(brand));
        }

        return pageDTO;
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

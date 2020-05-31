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
import vehicle.model.Brand;
import vehicle.model.Model;
import vehicle.repository.BrandRepo;
import vehicle.service.BrandService;
import vehicle.service.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ModelService modelService;

    @Override
    public BrandDTO convertToDTO(Brand brand) throws ConversionFailedError {
        try {
            return mapper.map(brand, BrandDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    @Override
    public Brand convertToModel(BrandDTO brandDTO) throws ConversionFailedError {
        try {
            return mapper.map(brandDTO, Brand.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
    }

    @Override
    public BrandDTO add(BrandDTO brandDTO) throws DuplicateEntity, ConversionFailedError {

        Brand newBrand = convertToModel(brandDTO);

        if (!brandRepo.existsByName(brandDTO.getName()))
            brandRepo.save(newBrand);
        else
            throw new DuplicateEntity("Item with name: "+brandDTO.getName()+" already exists");

        return brandDTO;
    }

    @Override
    public BrandDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> brand = brandRepo.findById(id);

        if (!brand.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            return convertToDTO(brand.get());
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

        Optional<Brand> change = brandRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(brandDTO.getName());

        List<Model> newModels = new ArrayList<>();

        for(ModelDTO m : brandDTO.getModels())
            newModels.add(modelService.convertToModel(m));

        change.get().setModels(newModels);

        brandRepo.save(change.get());

        return brandDTO;
    }

    @Override
    public BrandDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> deleted = brandRepo.findById(id);

        if (!deleted.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            brandRepo.deleteById(id);

        return convertToDTO(deleted.get());
    }
}

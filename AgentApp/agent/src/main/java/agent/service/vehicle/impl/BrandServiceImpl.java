package agent.service.vehicle.impl;

import agent.dto.shared.BrandDTO;
import agent.dto.shared.ModelDTO;
import agent.dto.vehicle.BrandPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Brand;
import agent.model.vehicle.Category;
import agent.model.vehicle.Model;
import agent.model.vehicle.mappings.BrandMapping;
import agent.model.vehicle.mappings.CategoryMapping;
import agent.repository.vehicle.BrandRepo;
import agent.repository.vehicle.mappingsRepo.BrandMappingRepo;
import agent.service.vehicle.BrandService;
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
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    BrandMappingRepo categoryMappingRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ModelService modelService;

    @Override
    @Transactional
    public BrandDTO convertToDTO(Brand brand) throws ConversionFailedError {
        try {
            BrandDTO brandDTO = mapper.map(brand, BrandDTO.class);
            brandDTO.setModels(new ArrayList<ModelDTO>());
            for (Model model: brand.getModels()){
                brandDTO.getModels().add(modelService.convertToDTO(model));
            }

            return brandDTO;
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

        if (!brandRepo.existsByName(brandDTO.getName())){
            newBrand.setModels(new ArrayList<>());
            Brand savedBrand = brandRepo.save(newBrand);

            return convertToDTO(savedBrand);
        } else {
            throw new DuplicateEntity("Brand with name: "+brandDTO.getName()+" already exists");
        }
    }

    @Override
    public BrandDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> brand = brandRepo.findById(id);

        if (!brand.isPresent()) {
            throw new EntityNotFound("No brand with ID: " + id);
        } else {
            return convertToDTO(brand.get());
        }
    }

    @Override
    public BrandPageDTO getAllPageable(Integer pageNo, String sortKey) throws ConversionFailedError {
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
    public List<BrandDTO> getAll() throws ConversionFailedError {
        List<Brand> brands = brandRepo.findAll();
        List<BrandDTO> brandDTOS = new ArrayList<>();
        for (Brand brand: brands){
            brandDTOS.add(convertToDTO(brand));
        }
        return brandDTOS;
    }

    @Override
    public BrandDTO update(Long id, BrandDTO brandDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> change = brandRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No brand with ID: "+id);

        change.get().setName(brandDTO.getName());

        brandDTO.setId(change.get().getId());
        Brand savedBrand = brandRepo.save(change.get());

        return brandDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> deleted = brandRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No brand with ID: "+id);
        } else {
            brandRepo.deleteById(id);
        }
    }

    @Override
    public BrandDTO deletePermanent(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> deleted = brandRepo.findById(id);

        if (!deleted.isPresent()){
            throw new EntityNotFound("No brand with ID: "+id);
        } else {
            brandRepo.deleteById(id);
        }
        return convertToDTO(deleted.get());
    }

    @Override
    public void addBrandViaMQ(saga.dto.BrandDTO brandDTO){
        Brand brand = brandRepo.findByName(brandDTO.getName());
        if (brand == null) {
            brand = mapper.map(brandDTO, Brand.class);
            brand.setId(null);
            brand = brandRepo.save(brand);
        }
        BrandMapping bm = categoryMappingRepo.findByBrandAgent(brand);
        if (bm == null) {
            bm = new BrandMapping();
            bm.setBrandAgent(brand);
            bm.setBrandBackId(brandDTO.getId());
            categoryMappingRepo.save(bm);
        }
    }
}

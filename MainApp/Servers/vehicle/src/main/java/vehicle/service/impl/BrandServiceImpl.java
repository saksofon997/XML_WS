package vehicle.service.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saga.commands.MainBrandCommand;
import saga.commands.TypeOfCommand;
import saga.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import saga.dto.ModelDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Model;
import vehicle.mq.VehiclePartsSender;
import vehicle.repository.BrandRepo;
import vehicle.service.BrandService;
import vehicle.service.ModelService;
import vehicle.soap.arrays.BrandArray;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepo brandRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ModelService modelService;

    @Autowired
    VehiclePartsSender vehiclePartsSender;

    @Inject
    private transient CommandGateway commandGateway;

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

            String brandAggregateId = UUID.randomUUID().toString();
            System.out.println(savedBrand.getId());
            // Send via MQ
            vehiclePartsSender.send(convertToDTO(savedBrand));
            // Send via SAGA
            commandGateway.send(new MainBrandCommand(savedBrand.getId(),brandDTO, TypeOfCommand.CREATE));
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
    @Transactional
    public BrandArray getAllSOAP(){
        List<Brand> brands = brandRepo.findAll();
        BrandArray brandArray = new BrandArray();
        for (Brand brand: brands) {
            vehicle.soap.dtos.BrandDTO brandDTO = new vehicle.soap.dtos.BrandDTO(brand.getId(),brand.getName());
            List<vehicle.soap.dtos.ModelDTO> modelDTOS = new ArrayList<vehicle.soap.dtos.ModelDTO>();
            for (Model model: brand.getModels()) {
                System.out.println("Model: " + model.getName());
                modelDTOS.add(new vehicle.soap.dtos.ModelDTO(model.getId(),model.getName()));
            }

            brandDTO.setModels(modelDTOS);
            brandArray.getItem().add(brandDTO);
        }

        brandArray.getItem().forEach((brand -> {
            System.out.println("Models for brand: " + brand.getName());
            for (vehicle.soap.dtos.ModelDTO modelDTO: brand.getModels()){
                System.out.println(modelDTO.getName());
            }
        }));
        return brandArray;
    }
    @Override
    public BrandDTO update(Long id, BrandDTO brandDTO) throws EntityNotFound, ConversionFailedError {

        Optional<Brand> change = brandRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No brand with ID: "+id);

        change.get().setName(brandDTO.getName());

//        List<Model> newModels = new ArrayList<>();

//        for(ModelDTO m : brandDTO.getModels()) {
//            newModels.add(modelService.convertToModel(m));
//        }

//        change.get().setModels(newModels);
        brandDTO.setId(change.get().getId());
        Brand savedBrand = brandRepo.save(change.get());
        commandGateway.send(new MainBrandCommand(savedBrand.getId(), brandDTO, TypeOfCommand.UPDATE));

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
            commandGateway.send(new MainBrandCommand(deleted.get().getId(), convertToDTO(deleted.get()), TypeOfCommand.DELETE));
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
}

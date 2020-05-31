package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vehicle.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Fuel;
import vehicle.model.Model;
import vehicle.repository.FuelRepo;
import vehicle.service.FuelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    FuelRepo fuelRepo;

    @Override
    public FuelDTO convertToDTO(Fuel fuel) throws ConversionFailedError {
        return new DozerBeanMapper().map(fuel, FuelDTO.class);
    }

    @Override
    public Fuel convertToModel(FuelDTO fuelDTO) throws ConversionFailedError {
        return new DozerBeanMapper().map(fuelDTO, Fuel.class);
    }

    @Override
    public FuelPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by(sortKey));
        Page<Fuel> pagedResult = fuelRepo.findAll(page);

        FuelPageDTO pageDTO = new FuelPageDTO();
        pageDTO.setPageNo(pagedResult.getNumber());
        pageDTO.setTotalPages(pagedResult.getTotalPages());
        for (Fuel fuel: pagedResult.getContent()){
            pageDTO.getContent().add(convertToDTO(fuel));
        }

        return pageDTO;
    }

    @Override
    public FuelDTO add(FuelDTO fuelDTO) throws DuplicateEntity, ConversionFailedError {
        Fuel fuelToCheck = fuelRepo.findByName(fuelDTO.getName());
        if (fuelToCheck != null){
            throw new DuplicateEntity("Fuel type with this name already exists");
        }
        Fuel fuel = convertToModel(fuelDTO);
        Fuel savedFuel = fuelRepo.save(fuel);
        return convertToDTO(savedFuel);
    }

    @Override
    public FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Fuel> fuel = fuelRepo.findById(id);
        if (fuel.isPresent()){
            return convertToDTO(fuel.get());
        } else {
            throw new EntityNotFound("Fuel type with this id not found.");
        }
    }

    @Override
    public FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound, ConversionFailedError {
        Optional<Fuel> check = fuelRepo.findById(id);
        if (!check.isPresent() || !id.equals(fuelDTO.getId())){
            throw new EntityNotFound("Fuel type not found, invalid data");
        }
        Fuel fuel = check.get();
        fuel.setName(fuelDTO.getName());
        Fuel savedFuel = fuelRepo.save(fuel);
        return convertToDTO(savedFuel);
    }

    @Override
    public FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError {
        Optional<Fuel> check = fuelRepo.findById(id);
        if (!check.isPresent() || !id.equals(id)){
            throw new EntityNotFound("Fuel type not found, invalid data");
        }
        Fuel fuel = check.get();
        fuel.setDeleted(true);
        return convertToDTO(fuelRepo.save(fuel));
    }
}

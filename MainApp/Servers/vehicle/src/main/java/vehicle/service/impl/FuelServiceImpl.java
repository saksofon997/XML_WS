package vehicle.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vehicle.dto.CategoryDTO;
import vehicle.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Category;
import vehicle.model.Fuel;
import vehicle.repository.FuelRepo;
import vehicle.service.FuelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    FuelRepo fuelRepo;

    @Autowired
    DozerBeanMapper mapper;

    public FuelDTO convertToDTO(Fuel fuel) throws ConversionFailedError {
        try {
            return mapper.map(fuel, FuelDTO.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Internal server error");
        }
    }

    public Fuel convertToModel(FuelDTO fuelDTO) throws ConversionFailedError {
        try {
            return mapper.map(fuelDTO, Fuel.class);
        } catch (Exception e) {
            throw new ConversionFailedError("Invalid data");
        }
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
    public FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity {

        Fuel newFuel = convertToModel(fuelDTO);

        if (!fuelRepo.existsByName(fuelDTO.getName()))
            fuelRepo.save(newFuel);
        else
            throw new DuplicateEntity("Item with name: "+fuelDTO.getName()+" already exists");

        return fuelDTO;
    }

    @Override
    public FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Fuel> fuel = fuelRepo.findById(id);

        if (!fuel.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            return convertToDTO(fuel.get());
    }

    @Override
    public FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound {

        Optional<Fuel> change = fuelRepo.findById(id);

        if (!change.isPresent())
            throw new EntityNotFound("No item with ID: "+id);

        change.get().setName(fuelDTO.getName());

        fuelRepo.save(change.get());

        return fuelDTO;
    }

    @Override
    public FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Fuel> deleted = fuelRepo.findById(id);

        if (!deleted.isPresent())
            throw new EntityNotFound("No item with ID: "+id);
        else
            fuelRepo.deleteById(id);

        return convertToDTO(deleted.get());
    }
}

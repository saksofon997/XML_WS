package search.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.dto.FuelDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Fuel;
import search.repository.FuelRepo;
import search.service.FuelService;

import java.util.List;
import java.util.Optional;

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
    public List<FuelDTO> getAll() {
        return null;
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

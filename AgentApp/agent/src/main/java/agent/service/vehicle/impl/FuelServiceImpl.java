package agent.service.vehicle.impl;

import agent.dto.shared.FuelDTO;
import agent.dto.vehicle.FuelPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.model.vehicle.Fuel;
import agent.model.vehicle.mappings.FuelMapping;
import agent.repository.vehicle.FuelRepo;
import agent.repository.vehicle.mappingsRepo.FuelMappingRepo;
import agent.service.vehicle.FuelService;
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
public class FuelServiceImpl implements FuelService {

    @Autowired
    FuelRepo fuelRepo;

    @Autowired
    FuelMappingRepo fuelMappingRepo;

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
    public FuelPageDTO getAllPageable(Integer pageNo, String sortKey) throws ConversionFailedError {
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
    public List<FuelDTO> getAll() throws ConversionFailedError {
        List<Fuel> fuels = fuelRepo.findAll();
        List<FuelDTO> fuelDTOS = new ArrayList<>();
        for (Fuel fuel: fuels){
            fuelDTOS.add(convertToDTO(fuel));
        }
        return fuelDTOS;
    }

    @Override
    public FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity {

        Fuel newFuel = convertToModel(fuelDTO);

        if (!fuelRepo.existsByName(fuelDTO.getName())) {
            Fuel savedFuel = fuelRepo.save(newFuel);
            return convertToDTO(savedFuel);
        } else {
            throw new DuplicateEntity("Fuel with name: " + fuelDTO.getName() + " already exists");
        }
    }

    @Override
    public FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Fuel> fuel = fuelRepo.findById(id);

        if (!fuel.isPresent()) {
            throw new EntityNotFound("No fuel with ID: " + id);
        } else {
            return convertToDTO(fuel.get());
        }
    }

    @Override
    public FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound {

        Optional<Fuel> change = fuelRepo.findById(id);

        if (!change.isPresent()) {
            throw new EntityNotFound("No fuel with ID: " + id);
        }
        change.get().setName(fuelDTO.getName());

        Fuel savedFuel = fuelRepo.save(change.get());

        return fuelDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) throws EntityNotFound, ConversionFailedError {

        Optional<Fuel> deleted = fuelRepo.findById(id);

        if (!deleted.isPresent()) {
            throw new EntityNotFound("No fuel with ID: " + id);
        }else {
            fuelRepo.deleteById(id);
        }
    }

    @Override
    public void addFuelViaMQ(saga.dto.FuelDTO fuelDTO){
        Fuel fuel = fuelRepo.findByName(fuelDTO.getName());
        if (fuel == null) {
            fuel = mapper.map(fuelDTO, Fuel.class);
            fuel.setId(null);
            fuel = fuelRepo.save(fuel);
        }
        FuelMapping fm = fuelMappingRepo.findByFuelAgent(fuel);
        if (fm == null) {
            fm = new FuelMapping();
            fm.setFuelAgent(fuel);
            fm.setFuelBackId(fuel.getId());
            fuelMappingRepo.save(fm);
        }

    }
}

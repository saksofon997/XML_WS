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
import saga.commands.TypeOfCommand;
import saga.commands.vehiclePartsCommands.MainFuelCommand;
import saga.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Fuel;
import vehicle.repository.FuelRepo;
import vehicle.service.FuelService;
import vehicle.model.FuelArray;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    FuelRepo fuelRepo;

    @Autowired
    DozerBeanMapper mapper;

    @Inject
    private transient CommandGateway commandGateway;

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
    public FuelArray getAllSOAP() {
        List<Fuel> fuels = fuelRepo.findAll();
        Fuel[] retVal = new Fuel[fuels.size()];
        FuelArray fuelArray = new FuelArray();
        fuelArray.getItem().addAll(fuels);
        for (Fuel fuel: fuelArray.getItem()) {
            System.out.println(fuel.getName());
        }

        return fuelArray;
    }

    @Override
    public FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity {

        Fuel newFuel = convertToModel(fuelDTO);

        if (!fuelRepo.existsByName(fuelDTO.getName())) {
            Fuel savedFuel = fuelRepo.save(newFuel);
            commandGateway.send(new MainFuelCommand(savedFuel.getId(), fuelDTO, TypeOfCommand.CREATE));
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
        commandGateway.send(new MainFuelCommand(savedFuel.getId(), fuelDTO, TypeOfCommand.UPDATE));

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
            commandGateway.send(new MainFuelCommand(deleted.get().getId(), convertToDTO(deleted.get()), TypeOfCommand.DELETE));
        }
    }
}

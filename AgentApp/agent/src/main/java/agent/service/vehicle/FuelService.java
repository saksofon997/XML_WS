package agent.service.vehicle;


import agent.dto.shared.FuelDTO;
import agent.dto.vehicle.FuelPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;

import java.util.List;

public interface FuelService {
    FuelPageDTO getAllPageable(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<FuelDTO> getAll() throws ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;

    void addFuelViaMQ(saga.dto.FuelDTO fuelDTO);
}

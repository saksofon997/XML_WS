package vehicle.service;

import vehicle.dto.FuelDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface FuelService {
    List<FuelDTO> getAll() throws EntityNotFound, ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound;

    FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

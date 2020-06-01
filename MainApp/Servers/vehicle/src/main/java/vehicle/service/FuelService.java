package vehicle.service;

import saga.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

public interface FuelService {
    FuelPageDTO getAll(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound;

    FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

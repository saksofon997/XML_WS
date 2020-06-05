package vehicle.service;

import saga.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;

import java.util.List;

public interface FuelService {
    FuelPageDTO getAllPageable(Integer pageNo, String sortKey) throws EntityNotFound, ConversionFailedError;

    List<FuelDTO> getAll() throws ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound;

    void delete(Long id) throws EntityNotFound, ConversionFailedError;
}

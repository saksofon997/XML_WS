package vehicle.service;

import vehicle.dto.BrandDTO;
import vehicle.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Brand;
import vehicle.model.Fuel;

import java.util.List;

public interface FuelService {

    FuelDTO convertToDTO(Fuel fuel) throws ConversionFailedError;

    Fuel convertToModel(FuelDTO fuelDTO) throws ConversionFailedError;

    FuelPageDTO getAll(Integer pageNo, String sortKey) throws ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws DuplicateEntity, ConversionFailedError;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound, ConversionFailedError;

    FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError;
}

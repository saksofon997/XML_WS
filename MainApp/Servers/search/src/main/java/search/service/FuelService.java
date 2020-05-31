package search.service;

import search.dto.FuelDTO;
import search.exceptions.ConversionFailedError;
import search.exceptions.DuplicateEntity;
import search.exceptions.EntityNotFound;
import search.model.Fuel;

import java.util.List;

public interface FuelService {
    List<FuelDTO> getAll();

    FuelDTO convertToDTO(Fuel fuel) throws ConversionFailedError;

    Fuel convertToModel(FuelDTO fuelDTO) throws ConversionFailedError;

    FuelDTO add(FuelDTO fuelDTO) throws DuplicateEntity, ConversionFailedError;

    FuelDTO getOne(Long id) throws EntityNotFound, ConversionFailedError;

    FuelDTO update(Long id, FuelDTO fuelDTO) throws EntityNotFound, ConversionFailedError;

    FuelDTO delete(Long id) throws EntityNotFound, ConversionFailedError;

}

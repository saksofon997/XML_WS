package search.service;

import search.dto.FuelDTO;

import java.util.List;

public interface FuelService {
    List<FuelDTO> getAll();

    FuelDTO add(FuelDTO fuelDTO);

    FuelDTO getOne(Long id);

    FuelDTO update(Long id, FuelDTO fuelDTO);

    FuelDTO delete(Long id);
}

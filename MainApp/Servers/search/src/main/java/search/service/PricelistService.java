package search.service;

import search.dto.PricelistDTO;

import java.util.List;

public interface PricelistService {
    PricelistDTO add(PricelistDTO pricelistDTO);

    PricelistDTO getOne(Long id);

    PricelistDTO update(Long id, Long pricelistDTO);

    PricelistDTO delete(Long id);

    List<PricelistDTO> getByOwner(Long ownerId);
}

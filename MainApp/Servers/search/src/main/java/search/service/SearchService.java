package search.service;

import search.dto.SearchResultDTO;

import java.util.List;

public interface SearchService {
    List<SearchResultDTO> doSearch(String brand, String category, String fuel,
                                   String model, String transmission, String location, long time);
}

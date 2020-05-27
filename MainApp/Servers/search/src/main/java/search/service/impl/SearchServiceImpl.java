package search.service.impl;

import search.dto.SearchResultDTO;
import search.service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService {
    @Override
    public List<SearchResultDTO> doSearch(String brand, String category, String fuel,
                                          String model, String transmission, String location, long time) {
        return null;
    }
}

package search.service;

import search.dto.SearchResultPageDTO;
import search.exceptions.ConversionFailedError;

public interface SearchService {
    SearchResultPageDTO doSearch(String brand, String category, String fuel,
                                 String model, String transmission,
                                 double loc_lat, double loc_long,
                                 long startTime, long endTime,
                                 Integer pageNo, String sortKey, Boolean cdw, Long mileage,
                                 Long priceFrom, Long priceTo, Integer childSeats, Long availableMileage,
                                 String order) throws ConversionFailedError;

}

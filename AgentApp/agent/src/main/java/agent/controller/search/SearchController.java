package agent.controller.search;

import agent.dto.search.SearchResultPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "search")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResultPageDTO> search(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "fuel", required = false) String fuel,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "transmission", required = false) String transmission,

            @RequestParam("loc_lat") double loc_lat,
            @RequestParam("loc_long") double loc_long,
            @RequestParam("start") long startTime,
            @RequestParam("end") long endTime,

            @RequestParam(value = "page", required = false) Integer pageNo,
            @RequestParam(value = "sort", required = false) String sort,

            @RequestParam(value = "cdw", required = false) Boolean cdw,
            @RequestParam(value = "mileage", required = false) Long mileage,
            @RequestParam(value = "availableMileage", required = false) Long availableMileage,
            @RequestParam(value = "priceFrom", required = false) Long priceFrom,
            @RequestParam(value = "priceTo", required = false) Long priceTo,
            @RequestParam(value = "childSeats", required = false) Integer childSeats
    ) throws ConversionFailedError {

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        SearchResultPageDTO vehicles = searchService.doSearch(brand, category, fuel, model,
                transmission, loc_lat, loc_long,
                startTime, endTime, pageNo, sort, cdw, mileage, priceFrom, priceTo, childSeats, availableMileage);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}

package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import search.dto.SearchResultPageDTO;
import search.exceptions.ConversionFailedError;
import search.service.SearchService;

import java.util.List;

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
                    @RequestParam(value = "sort", required = false) String sort
                    ) throws ConversionFailedError {

        sort = (sort != null) ? sort: "id";
        pageNo = (pageNo != null) ? pageNo: 0;

        SearchResultPageDTO vehicles = searchService.doSearch(brand, category, fuel, model,
                                                                transmission, loc_lat, loc_long,
                                                                startTime, endTime, pageNo, sort);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}

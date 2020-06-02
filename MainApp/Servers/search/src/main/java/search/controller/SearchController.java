package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import search.dto.SearchResultDTO;
import search.service.SearchService;

import java.util.List;

@RestController
@RequestMapping(value = "search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchResultDTO>> search(
                    @RequestParam(value = "brand", required = false) String brand,
                    @RequestParam(value = "category", required = false) String category,
                    @RequestParam(value = "fuel", required = false) String fuel,
                    @RequestParam(value = "model", required = false) String model,
                    @RequestParam(value = "transmission", required = false) String transmission,

                    @RequestParam("location") String location,
                    @RequestParam("start") long startTime,
                    @RequestParam("end") long endTime

                    ) {


        List<SearchResultDTO> vehicles = searchService.doSearch(brand, category, fuel, model,
                                                                transmission, location, startTime);

        return new ResponseEntity<>(vehicles, HttpStatus.ACCEPTED);
    }
}

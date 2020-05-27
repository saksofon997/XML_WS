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
@RequestMapping(value = "api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchResultDTO>> search(@RequestParam("brand") String brand,
                                                        @RequestParam("category") String category,
                                                        @RequestParam("fuel") String fuel,
                                                        @RequestParam("model") String model,
                                                        @RequestParam("transmission") String transmission,

                                                        @RequestParam("location") String location,
                                                        @RequestParam("time") long time
                                                        ) {

        List<SearchResultDTO> vehicles = searchService.doSearch(brand, category, fuel, model,
                                                                transmission, location, time);

        return new ResponseEntity<>(vehicles, HttpStatus.ACCEPTED);
    }
}

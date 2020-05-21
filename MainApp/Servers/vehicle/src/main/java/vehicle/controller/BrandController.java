package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.exceptions.ItemNotFound;
import vehicle.service.BrandService;
import vehicle.service.ValidationService;

@RestController
@RequestMapping(value = "api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    ValidationService validationService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getAll() throws ItemNotFound {

        brandService.getAll();

        return new ResponseEntity<>("GET: /brand", HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = "application/json",
                 produces = "application/json")
    public ResponseEntity<String> createNew(@RequestBody String brandDTO) {

        return new ResponseEntity<>(brandDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String id) {

        return new ResponseEntity<>("GET: /brand/"+id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody String brandDTO) {

        return new ResponseEntity<>("PUT: /brand/"+id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) {

        return new ResponseEntity<>("DELETE: /brand/"+id, HttpStatus.ACCEPTED);
    }
}
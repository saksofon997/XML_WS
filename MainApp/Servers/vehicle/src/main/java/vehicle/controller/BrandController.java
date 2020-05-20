package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.exceptions.ItemNotFound;
import vehicle.service.BrandService;
import vehicle.utils.ErrorMessages;

@RestController
@RequestMapping(value = "api")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(path = "/brand",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() throws ItemNotFound {

        brandService.getAll();

        return new ResponseEntity<>("GET: /brand", HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/brand",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNew(@RequestBody String brandDTO) {
        if (brandDTO == null){
            return new ResponseEntity<>(ErrorMessages.REQUEST_ERROR(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(brandDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/brand/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String id) {

        return new ResponseEntity<>("GET: /brand/"+id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/brand/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody String brandDTO) {

        return new ResponseEntity<>("PUT: /brand/"+id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/brand/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) {

        return new ResponseEntity<>("DELETE: /brand/"+id, HttpStatus.ACCEPTED);
    }
}
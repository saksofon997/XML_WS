package vehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pricelist")
public class PricelistController {

    @PostMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNew(@RequestBody String pricelistDTO) {

        return new ResponseEntity<>(pricelistDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody String pricelistDTO) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/owner/{ownerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable String ownerId) {

        return new ResponseEntity<>(ownerId, HttpStatus.ACCEPTED);
    }
}

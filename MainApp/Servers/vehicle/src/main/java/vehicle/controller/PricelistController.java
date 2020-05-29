package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.PricelistDTO;
import vehicle.service.PricelistService;

import java.util.List;

@RestController
@RequestMapping(value = "api/pricelist")
public class PricelistController {

    @Autowired
    PricelistService pricelistService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricelistDTO> createNew(@RequestBody PricelistDTO pricelistDTO) {

        PricelistDTO added = pricelistService.add(pricelistDTO);

        return new ResponseEntity<>(pricelistDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricelistDTO> getOne(@PathVariable Long id) {

        PricelistDTO pricelistDTO = pricelistService.getOne(id);

        return new ResponseEntity<>(pricelistDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricelistDTO> update(@PathVariable Long id,
                                               @RequestBody Long pricelistDTO) {

        PricelistDTO updated = pricelistService.update(id, pricelistDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricelistDTO> delete(@PathVariable Long id) {

        PricelistDTO deleted = pricelistService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/owner/{ownerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PricelistDTO>> get(@PathVariable Long ownerId) {

        List<PricelistDTO> pricing = pricelistService.getByOwner(ownerId);

        return new ResponseEntity<>(pricing, HttpStatus.ACCEPTED);
    }
}

package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.FuelDTO;
import vehicle.service.FuelService;

import java.util.List;

@RestController
@RequestMapping(value = "api/fuel")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FuelDTO>> getAll() {

        List<FuelDTO> fuels = fuelService.getAll();

        return new ResponseEntity<>(fuels, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> createNew(@RequestBody FuelDTO fuelDTO) {

        FuelDTO added = fuelService.add(fuelDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> getOne(@PathVariable Long id) {

        FuelDTO fuelDTO = fuelService.getOne(id);

        return new ResponseEntity<>(fuelDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> update(@PathVariable Long id,
                                          @RequestBody FuelDTO fuelDTO) {

        FuelDTO updated = fuelService.update(id, fuelDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> delete(@PathVariable Long id) {

        FuelDTO deleted = fuelService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

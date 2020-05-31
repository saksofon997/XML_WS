package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.FuelService;

import java.util.List;

@RestController
@RequestMapping(value = "fuel")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelPageDTO> getAll(
            @RequestParam(value = "page", required = false) Integer pageNo,
            @RequestParam(value = "sort", required = false) String sort) throws ConversionFailedError, EntityNotFound {
        sort = (sort != null) ? sort: "id";
        pageNo = (pageNo != null) ? pageNo: 0;
        FuelPageDTO fuels = fuelService.getAll(pageNo, sort);

        return new ResponseEntity<>(fuels, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> createNew(@RequestBody FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity {

        FuelDTO added = fuelService.add(fuelDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        FuelDTO fuelDTO = fuelService.getOne(id);

        return new ResponseEntity<>(fuelDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> update(@PathVariable Long id,
                                          @RequestBody FuelDTO fuelDTO) throws EntityNotFound {

        FuelDTO updated = fuelService.update(id, fuelDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        FuelDTO deleted = fuelService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

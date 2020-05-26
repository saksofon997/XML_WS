package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.VehicleDTO;
import vehicle.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping(value = "api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getAll() {

        List<VehicleDTO> vehicles = vehicleService.getAll();

        return new ResponseEntity<>(vehicles, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createNew(@RequestBody VehicleDTO vehicleDTO) {

        VehicleDTO added = vehicleService.add(vehicleDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getOne(@PathVariable Long id) {

        VehicleDTO vehicleDTO = vehicleService.getOne(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id,
                                             @RequestBody VehicleDTO vehicleDTO) {

        VehicleDTO updated = vehicleService.update(id, vehicleDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) {

        VehicleDTO deleted = vehicleService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

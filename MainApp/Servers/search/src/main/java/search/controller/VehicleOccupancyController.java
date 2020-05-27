package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.VehicleOccupancyDTO;
import vehicle.service.VehicleOccupancyService;

import java.util.List;

@RestController
@RequestMapping(value = "api")
public class VehicleOccupancyController {

    @Autowired
    VehicleOccupancyService vehicleOccupancyService;

    @GetMapping(path = "/vehicle/{vehicleId}/occupancy",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleOccupancyDTO>> get(@PathVariable Long vehicleId) {

        List<VehicleOccupancyDTO> occupancies = vehicleOccupancyService.getAll(vehicleId);

        return new ResponseEntity<>(occupancies, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/vehicle/{vehicleId}/occupancy",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> createNew(@PathVariable Long vehicleId,
                                                         @RequestBody VehicleOccupancyDTO vehicleOccupancyDTO) {

        VehicleOccupancyDTO added = vehicleOccupancyService.add(vehicleId, vehicleOccupancyDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/vehicle/{vehicleId}/occupancy/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> update(@PathVariable Long vehicleId,
                                                      @PathVariable Long id,
                                                      @RequestBody VehicleOccupancyDTO vehicleOccupancyDTO) {

        VehicleOccupancyDTO updated = vehicleOccupancyService.update(vehicleId, id, vehicleOccupancyDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/vehicle/{vehicleId}/occupancy/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> delete(@PathVariable Long vehicleId,
                                                      @PathVariable Long id) {

        VehicleOccupancyDTO deleted = vehicleOccupancyService.delete(vehicleId, id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

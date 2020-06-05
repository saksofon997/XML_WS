package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saga.dto.VehicleOccupancyDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.VehicleOccupancyService;

import java.util.List;

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = "*")
public class VehicleOccupancyController {

    @Autowired
    VehicleOccupancyService vehicleOccupancyService;

    @GetMapping(path = "/vehicle/{vehicleId}/occupancy",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleOccupancyDTO>> get(@PathVariable Long vehicleId) throws ConversionFailedError, EntityNotFound {

        List<VehicleOccupancyDTO> occupancies = vehicleOccupancyService.getAll(vehicleId);

        return new ResponseEntity<>(occupancies, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/vehicle/{vehicleId}/occupancy",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> createNew(@PathVariable Long vehicleId,
                                                         @RequestBody VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity {

        VehicleOccupancyDTO added = vehicleOccupancyService.add(vehicleId, vehicleOccupancyDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/vehicle/{vehicleId}/occupancy/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> update(@PathVariable Long vehicleId,
                                                      @PathVariable Long id,
                                                      @RequestBody VehicleOccupancyDTO vehicleOccupancyDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity {

        VehicleOccupancyDTO updated = vehicleOccupancyService.update(vehicleId, id, vehicleOccupancyDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/vehicle/{vehicleId}/occupancy/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleOccupancyDTO> delete(@PathVariable Long vehicleId,
                                                      @PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        VehicleOccupancyDTO deleted = vehicleOccupancyService.delete(vehicleId, id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/vehicle/{vehicleId}/occupancy/start/{start_time}/end/{end_time}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleOccupancyDTO>> getOccupanciesOfGivenPeriod(@PathVariable Long vehicleId,
                                                      @PathVariable long start_time,
                                                      @PathVariable long end_time) throws ConversionFailedError, EntityNotFound {

        List<VehicleOccupancyDTO> occupancies = vehicleOccupancyService.getOccupanciesOfGivenPeriod(vehicleId, start_time, end_time);

        return new ResponseEntity<>(occupancies, HttpStatus.ACCEPTED);
    }
}

package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saga.dto.FuelDTO;
import vehicle.dto.FuelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.mq.VehiclePartsSender;
import vehicle.service.FuelService;

import java.util.List;

@RestController
@RequestMapping(value = "fuel")
@CrossOrigin(origins = "*")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @Autowired
    VehiclePartsSender vehiclePartsSender;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
            @RequestHeader(value = "page", required = false) Integer pageNo,
            @RequestHeader(value = "sort", required = false) String sort,
            @RequestHeader(value = "pageable", required = false) Boolean pageable) throws ConversionFailedError, EntityNotFound {
        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        if (pageable) {
            FuelPageDTO page = fuelService.getAllPageable(pageNo, sort);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            List<FuelDTO> allFuels = fuelService.getAll();
            return new ResponseEntity<>(allFuels, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<FuelDTO> createNew(@RequestBody FuelDTO fuelDTO) throws ConversionFailedError, DuplicateEntity {

        FuelDTO added = fuelService.add(fuelDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuelDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        FuelDTO fuelDTO = fuelService.getOne(id);
        vehiclePartsSender.send(fuelDTO);
        return new ResponseEntity<>(fuelDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<FuelDTO> update(@PathVariable Long id,
                                          @RequestBody FuelDTO fuelDTO) throws EntityNotFound {

        FuelDTO updated = fuelService.update(id, fuelDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<FuelDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        fuelService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

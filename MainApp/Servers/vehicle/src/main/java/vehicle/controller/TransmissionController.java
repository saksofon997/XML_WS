package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saga.dto.TransmissionDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.TransmissionService;

import java.util.List;

@RestController
@RequestMapping(value = "transmission")
@CrossOrigin(origins = "*")
public class TransmissionController {

    @Autowired
    TransmissionService transmissionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransmissionDTO>> getAll() throws ConversionFailedError, EntityNotFound {

        List<TransmissionDTO> transmissions = transmissionService.getAll();

        return new ResponseEntity<>(transmissions, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<TransmissionDTO> createNew(@RequestBody TransmissionDTO transmissionDTO) throws ConversionFailedError, DuplicateEntity {

        TransmissionDTO added = transmissionService.add(transmissionDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransmissionDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        TransmissionDTO transmissionDTO = transmissionService.getOne(id);

        return new ResponseEntity<>(transmissionDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<TransmissionDTO> update(@PathVariable Long id,
                                                  @RequestBody TransmissionDTO transmissionDTO) throws EntityNotFound, ConversionFailedError {

        TransmissionDTO updated = transmissionService.update(id, transmissionDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<TransmissionDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        transmissionService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

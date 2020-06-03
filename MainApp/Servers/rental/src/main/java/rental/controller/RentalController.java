package rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rental.dto.RentalDTO;
import rental.exceptions.*;
import rental.service.RentalService;

@RestController
@RequestMapping(value = "/rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_RENTAL_PERMISSION')")
    public ResponseEntity<RentalDTO> createNew(@RequestBody RentalDTO rentalDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound {

        RentalDTO added = rentalService.add(rentalDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDTO> getOne(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        RentalDTO rentalDTO = rentalService.getOne(id);

        return new ResponseEntity<>(rentalDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_RENTAL_PERMISSION')")
    public ResponseEntity<RentalDTO> update(@PathVariable Long id,
                                          @RequestBody RentalDTO rentalDTO) throws EntityNotFound, UnexpectedError, ConversionFailedError {

        RentalDTO updated = rentalService.update(id, rentalDTO);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_RENTAL_PERMISSION')")
    public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        rentalService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

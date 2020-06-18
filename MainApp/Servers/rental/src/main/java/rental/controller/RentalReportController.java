package rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rental.dto.RentalReportDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.exceptions.EntityNotFound;
import rental.service.RentalReportService;

@RestController
@RequestMapping(value = "/rental")
@CrossOrigin(origins = "*")
public class RentalReportController {

    @Autowired
    RentalReportService rentalReportService;

    @PostMapping(path = "/{id}/rental_report",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_RENTAL_PERMISSION')")
    public ResponseEntity<RentalReportDTO> createNew(@RequestBody RentalReportDTO rentalReportDTO) throws DuplicateEntity, ConversionFailedError, EntityNotFound {

        RentalReportDTO added = rentalReportService.add(rentalReportDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }
}

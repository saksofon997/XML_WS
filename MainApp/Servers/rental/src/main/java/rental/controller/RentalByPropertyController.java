package rental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rental.exceptions.*;

@RestController
@RequestMapping(value = "")
// No inspiration for better name... Might not need it /rental/customer/{id} (?)
public class RentalByPropertyController {


    @GetMapping(path = "/customer/{id}/rental",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsOfCustomer(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/vehicle/{id}/rental",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsByVehicle(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {


        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/owner/{id}/rental",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsOfOwner(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        return new ResponseEntity<>("userDTO", HttpStatus.ACCEPTED);
    }
}

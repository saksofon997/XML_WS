package rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rental.dto.RentalPageDTO;
import rental.exceptions.*;
import rental.mq.RentalMQSender;
import rental.service.RentalService;

import java.util.Objects;

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = "*")
// No inspiration for better name... Might not need it /rental/customer/{id} (?)
public class RentalByPropertyController {

    @Autowired
    RentalService rentalService;

    @GetMapping(path = "/customer/{id}/rental/status/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsOfCustomer(@PathVariable Long id,
                                                  @PathVariable String status,
                                                  @RequestHeader(value = "page", required = false) Integer pageNo,
                                                  @RequestHeader(value = "sort", required = false) String sort,
                                                  @RequestHeader(value = "pageable", required = false) Boolean pageable,
                                                  @RequestAttribute("userId") Long customerId) throws EntityNotFound, ConversionFailedError {

        if (id != customerId) {
            throw new EntityNotFound("Invalid customer request");
        }

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        RentalPageDTO page = rentalService.getByCustomerAndByStatusPageable(pageNo, sort, id, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(path = "/vehicle/{id}/rental",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsByVehicle(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {


        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/owner/{id}/rental/status/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentalsOfOwner(@PathVariable Long id,
                                               @PathVariable String status,
                                               @RequestHeader(value = "page", required = false) Integer pageNo,
                                               @RequestHeader(value = "sort", required = false) String sort,
                                               @RequestHeader(value = "pageable", required = false) Boolean pageable,
                                               @RequestAttribute("userId") Long customerId) throws EntityNotFound, ConversionFailedError {

        if (!Objects.equals(id, customerId)) {
            throw new EntityNotFound("Invalid owner request");
        }

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        RentalPageDTO page = rentalService.getByOwnerAndByStatusPageable(pageNo, sort, id, status);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}

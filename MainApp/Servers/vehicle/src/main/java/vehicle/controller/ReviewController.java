package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saga.dto.BrandDTO;
import saga.dto.ReviewDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.dto.ReviewPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.ReviewService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping(path = "/review",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('APPROVE_REVIEW_PERMISSION')")
    public ResponseEntity<?> getPending(
            @RequestHeader(value = "page", required = false) Integer pageNo,
            @RequestHeader(value = "sort", required = false) String sort,
            @RequestHeader(value = "pageable", required = false) Boolean pageable
    ) throws ConversionFailedError, EntityNotFound {

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        if (pageable) {
            ReviewPageDTO page = reviewService.getAllPageable(pageNo, sort);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            List<ReviewDTO> pending = reviewService.getPending();
            return new ResponseEntity<>(pending, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/vehicle/{vehicleId}/review",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewDTO>> get(@PathVariable Long vehicleId) throws ConversionFailedError, EntityNotFound {

        List<ReviewDTO> reviews = reviewService.getByVehicle(vehicleId);

        return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/vehicle/{vehicleId}/review",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_REVIEW_PERMISSION')")
    public ResponseEntity<ReviewDTO> createNew(@PathVariable Long vehicleId,
                                               @RequestBody ReviewDTO reviewDTO) throws ConversionFailedError {

        ReviewDTO added = reviewService.add(vehicleId, reviewDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/vehicle/{vehicleId}/review/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> getOne(@PathVariable Long vehicleId,
                                            @PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        ReviewDTO reviewDTO = reviewService.getOne(vehicleId, id);

        return new ResponseEntity<>(reviewDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/vehicle/{vehicleId}/review/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_REVIEW_PERMISSION') or hasAuthority('APPROVE_REVIEW_PERMISSION')")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long vehicleId,
                                            @PathVariable Long id,
                                            @RequestBody ReviewDTO reviewDTO) throws ConversionFailedError, EntityNotFound {

        ReviewDTO updated = reviewService.update(vehicleId, id, reviewDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/vehicle/{vehicleId}/review/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_REVIEW_PERMISSION')")
    public ResponseEntity<ReviewDTO> delete(@PathVariable Long vehicleId,
                                            @PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        ReviewDTO deleted = reviewService.delete(vehicleId, id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }

    //TODO Add patch mapping for approving reviews?
}

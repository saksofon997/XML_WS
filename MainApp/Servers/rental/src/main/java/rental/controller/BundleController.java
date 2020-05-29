package rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rental.dto.BundleDTO;
import rental.exceptions.ConversionFailedError;
import rental.exceptions.DuplicateEntity;
import rental.service.BundleService;

@RestController
@RequestMapping(value = "/bundle")
public class BundleController {

    @Autowired
    BundleService bundleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_RENTAL_PERMISSION')")
    public ResponseEntity<BundleDTO> createNew(@RequestBody BundleDTO bundleDTO) throws DuplicateEntity, ConversionFailedError {

        BundleDTO added = bundleService.add(bundleDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }
}

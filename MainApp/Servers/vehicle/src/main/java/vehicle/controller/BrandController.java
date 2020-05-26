package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.BrandDTO;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.UnexpectedError;
import vehicle.service.BrandService;
import vehicle.service.ValidationService;

import java.util.List;

@RestController
@RequestMapping(value = "api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandDTO>> getAll() throws EntityNotFound {

        List<BrandDTO> brands = brandService.getAll();

        return new ResponseEntity<>(brands, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> createNew(@RequestBody BrandDTO brandDTO) throws DuplicateEntity {

        BrandDTO added = brandService.add(brandDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> getOne(@PathVariable Long id) throws EntityNotFound {

        BrandDTO brandDTO = brandService.getOne(id);

        return new ResponseEntity<>(brandDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> update(@PathVariable Long id,
                                           @RequestBody BrandDTO brandDTO) throws UnexpectedError {

        BrandDTO updated = brandService.update(id, brandDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> delete(@PathVariable Long id) throws EntityNotFound {

        BrandDTO deleted = brandService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}
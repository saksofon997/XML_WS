package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saga.dto.BrandDTO;
import vehicle.dto.BrandPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.exceptions.UnexpectedError;
import vehicle.service.BrandService;

import java.util.List;

@RestController
@RequestMapping(value = "brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandPageDTO> getAll(
            @RequestParam(value = "page", required = false) Integer pageNo,
            @RequestParam(value = "sort", required = false) String sort) throws ConversionFailedError, EntityNotFound {

        sort = (sort != null) ? sort: "id";
        pageNo = (pageNo != null) ? pageNo: 0;
        BrandPageDTO page = brandService.getAll(pageNo, sort);
        return new ResponseEntity<>(page, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> createNew(@RequestBody BrandDTO brandDTO) throws DuplicateEntity, ConversionFailedError {

        BrandDTO added = brandService.add(brandDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> getOne(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        BrandDTO brandDTO = brandService.getOne(id);

        return new ResponseEntity<>(brandDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> update(@PathVariable Long id,
                                           @RequestBody BrandDTO brandDTO) throws UnexpectedError, ConversionFailedError, EntityNotFound {

        BrandDTO updated = brandService.update(id, brandDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDTO> delete(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        BrandDTO deleted = brandService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

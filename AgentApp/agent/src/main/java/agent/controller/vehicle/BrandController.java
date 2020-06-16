package agent.controller.vehicle;

import agent.dto.shared.BrandDTO;
import agent.dto.vehicle.BrandPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.exceptions.UnexpectedError;
import agent.service.vehicle.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "brand")
@CrossOrigin(origins = "*")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
            @RequestHeader(value = "page", required = false) Integer pageNo,
            @RequestHeader(value = "sort", required = false) String sort,
            @RequestHeader(value = "pageable", required = false) Boolean pageable) throws ConversionFailedError, EntityNotFound {

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        if (pageable) {
            BrandPageDTO page = brandService.getAllPageable(pageNo, sort);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            List<BrandDTO> allBrands = brandService.getAll();
            return new ResponseEntity<>(allBrands, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_VEHICLE_PART_PERMISSION')")
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
    @PreAuthorize("hasAuthority('CHANGE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id,
                                           @RequestBody BrandDTO brandDTO) throws UnexpectedError, ConversionFailedError, EntityNotFound {

        BrandDTO updated = brandService.update(id, brandDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<BrandDTO> delete(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        brandService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

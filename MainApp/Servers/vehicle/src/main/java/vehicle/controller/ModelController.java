package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saga.dto.ModelDTO;
import vehicle.dto.ModelPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.ModelService;

import java.util.List;

@RestController
@RequestMapping(value = "brand")
@CrossOrigin(origins = "*")
public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping(path = "/{brandId}/model",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@PathVariable Long brandId,
                                    @RequestHeader(value = "page", required = false) Integer pageNo,
                                    @RequestHeader(value = "sort", required = false) String sort,
                                    @RequestHeader(value = "pageable", required = false) Boolean pageable)
            throws ConversionFailedError, EntityNotFound {

        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;

        if (pageable) {
            ModelPageDTO models = modelService.getAllPageable(brandId, pageNo, sort);
            return new ResponseEntity<>(models, HttpStatus.OK);
        } else {
            List<ModelDTO> allModels = modelService.getAll(brandId);
            return new ResponseEntity<>(allModels, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/{brandId}/model",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<ModelDTO> createNew(@PathVariable Long brandId,
                                              @RequestBody ModelDTO modelDTO) throws ConversionFailedError, EntityNotFound, DuplicateEntity {

        ModelDTO added = modelService.add(brandId, modelDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDTO> getOne(@PathVariable Long brandId,
                                           @PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        ModelDTO modelDTO = modelService.getOne(brandId, id);

        return new ResponseEntity<>(modelDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{brandId}/model/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<ModelDTO> update(@PathVariable Long brandId,
                                           @PathVariable Long id,
                                           @RequestBody ModelDTO modelDTO) throws EntityNotFound, ConversionFailedError {

        ModelDTO updated = modelService.update(brandId, id, modelDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<ModelDTO> delete(@PathVariable Long brandId,
                                           @PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        modelService.delete(brandId, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.ModelDTO;
import vehicle.model.Model;
import vehicle.service.ModelService;

import java.util.List;

@RestController
@RequestMapping(value = "api/brand")
public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping(path = "/{brandId}/model",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDTO>> getAll(@PathVariable Long brandId) {

        List<ModelDTO> models = modelService.getAll(brandId);

        return new ResponseEntity<>(models, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/{brandId}/model",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDTO> createNew(@PathVariable Long brandId,
                                              @RequestBody ModelDTO modelDTO) {

        ModelDTO added = modelService.add(brandId, modelDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{brandId}/model/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDTO> getOne(@PathVariable Long brandId,
                                           @PathVariable Long id) {

        ModelDTO modelDTO = modelService.getOne(brandId, id);

        return new ResponseEntity<>(modelDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{brandId}/model/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDTO> update(@PathVariable Long brandId,
                                           @PathVariable Long id,
                                           @RequestBody ModelDTO modelDTO) {

        ModelDTO updated = modelService.update(brandId, id, modelDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{brandId}/model/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDTO> delete(@PathVariable Long brandId,
                                           @PathVariable Long id) {

        ModelDTO deleted = modelService.delete(brandId, id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

package vehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/brand")
public class ModelController {

    @GetMapping(path = "/{brandId}/model",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(@PathVariable String brandId) {

        return new ResponseEntity<>(brandId, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/{brandId}/model",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNew(@PathVariable String brandId, @RequestBody String modelDTO) {

        return new ResponseEntity<>(brandId, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String brandId, @PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String brandId,
                                         @PathVariable String id,
                                         @RequestBody String modelDTO) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String brandId, @PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }
}

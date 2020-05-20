package vehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
public class ModelController {

    @GetMapping(path = "/brand/{brandId}/model",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(@PathVariable String brandId) {

        return new ResponseEntity<>(brandId, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/brand/{brandId}/model",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNew(@PathVariable String brandId, @RequestBody String modelDTO) {

        return new ResponseEntity<>(brandId, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/brand/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String brandId, @PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/brand/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String brandId,
                                         @PathVariable String id,
                                         @RequestBody String modelDTO) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/brand/{brandId}/model/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String brandId, @PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }
}

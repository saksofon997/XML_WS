package vehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/transmission")
public class TransmissionController {

    @GetMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {

        return new ResponseEntity<>("transmission", HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNew(@RequestBody String transmissionDTO) {

        return new ResponseEntity<>(transmissionDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOne(@PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody String transmissionDTO) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) {

        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }
}

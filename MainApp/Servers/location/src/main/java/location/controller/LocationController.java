package location.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "location")
public class LocationController {

    @GetMapping(path = "/test",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatus() {
        return "CAO JA SAM LOCATION SERVIS";
    }
}

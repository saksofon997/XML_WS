package vehicle.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class TestController {

    @GetMapping(path = "/test",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClinic() {
        return "CAO JA SAM VEHICLE SERVIS izmenjen malo";
    }
}

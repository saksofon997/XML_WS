package vehicle.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saga.dto.VehicleDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.service.VehicleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getAll() {

        List<VehicleDTO> vehicles = vehicleService.getAll();

        return new ResponseEntity<>(vehicles, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createNew(@RequestPart("images") MultipartFile[] images, @RequestPart("vehicle") VehicleDTO vehicleDTO) throws DuplicateEntity, ConversionFailedError {

        VehicleDTO added = vehicleService.add(vehicleDTO, images, request);
        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        VehicleDTO vehicleDTO = vehicleService.getOne(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id,
                                             @RequestBody VehicleDTO vehicleDTO) throws ConversionFailedError, EntityNotFound {

        VehicleDTO updated = vehicleService.update(id, vehicleDTO);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        VehicleDTO deleted = vehicleService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "image/{path}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable String path, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Resource image = null;
        try {
            image = vehicleService.getImage(path, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
            assert image != null;
            contentType = request.getServletContext().getMimeType(image.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        InputStream in = image.getInputStream();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping(value = "/{id}/token")
    public void getVehicleToken(@PathVariable Long id) {

        TokenDTO token = vehicleService.getToken(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }
}

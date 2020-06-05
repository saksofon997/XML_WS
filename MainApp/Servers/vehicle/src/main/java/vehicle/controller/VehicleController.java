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
import vehicle.exceptions.OperationNotAllowed;
import vehicle.service.VehicleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping(path = "/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getAll(HttpServletRequest request) {
        Long isAgent = (Long)request.getAttribute("userId");
        System.out.println("User ID: ");
        System.out.println(isAgent);
        List<VehicleDTO> vehicles = vehicleService.getAll();

        return new ResponseEntity<>(vehicles, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/vehicle",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> createNew(@RequestPart("images") MultipartFile[] images, @RequestPart("vehicle") VehicleDTO vehicleDTO, HttpServletRequest request) throws DuplicateEntity, ConversionFailedError, OperationNotAllowed {

        VehicleDTO added = vehicleService.add(vehicleDTO, images, request, false);
        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/vehicle/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        VehicleDTO vehicleDTO = vehicleService.getOne(id);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/owner/{id}/vehicle",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getOwnersVehicles(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        List<VehicleDTO> vehicleDTOS = vehicleService.getOwnersVehicles(id);

        return new ResponseEntity<>(vehicleDTOS, HttpStatus.OK);
    }

    @PutMapping(path = "/vehicle/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id,
                                             @RequestBody VehicleDTO vehicleDTO) throws ConversionFailedError, EntityNotFound {

        VehicleDTO updated = vehicleService.update(id, vehicleDTO);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/vehicle/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        VehicleDTO deleted = vehicleService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/vehicle/image/{path}", method = RequestMethod.GET)
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
}

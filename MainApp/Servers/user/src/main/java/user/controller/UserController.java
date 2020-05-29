package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import user.dto.UserDTO;
import user.exceptions.*;
import user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET })
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAll() throws ConversionFailedError {

        List<UserDTO> brands = userService.getAll();

        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_USER_PERMISSION')")
    public ResponseEntity<UserDTO> createNew(@RequestBody UserDTO userDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError {

        UserDTO added = userService.add(userDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        UserDTO brandDTO = userService.getOne(id);

        return new ResponseEntity<>(brandDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                           @RequestBody UserDTO userDTO) throws EntityNotFound, UnexpectedError {

        UserDTO updated = userService.update(id, userDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) throws EntityNotFound {

        UserDTO deleted = userService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

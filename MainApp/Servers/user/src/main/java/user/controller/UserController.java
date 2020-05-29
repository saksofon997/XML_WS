package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import user.dto.UserDTO;
import user.dto.UserPageDTO;
import user.exceptions.*;
import user.service.UserService;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPageDTO> getAll(
            @RequestParam(value = "page", required = false) Integer pageNo,
            @RequestParam(value = "sort", required = false) String sort
            ) throws ConversionFailedError {

        sort = (sort != null) ? sort: "id";
        pageNo = (pageNo != null) ? pageNo: 0;

        UserPageDTO page = userService.getAll(pageNo, sort);

        return new ResponseEntity<>(page, HttpStatus.OK);
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

        UserDTO userDTO = userService.getOne(id);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_USER_PERMISSION')")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                           @RequestBody UserDTO userDTO) throws EntityNotFound, UnexpectedError, ConversionFailedError {

        UserDTO updated = userService.update(id, userDTO);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ACTIVATE_USER_PERMISSION') or hasAuthority('DEACTIVATE_USER_PERMISSION')")
    public ResponseEntity<UserDTO> activateOrDeactivate(@PathVariable Long id,
                                          @RequestBody UserDTO userDTO) throws EntityNotFound, UnexpectedError, ConversionFailedError {

        UserDTO updated = userService.activateOrDeactivate(id, userDTO);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REMOVE_USER_PERMISSION')")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) throws EntityNotFound, ConversionFailedError {

        UserDTO deleted = userService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}

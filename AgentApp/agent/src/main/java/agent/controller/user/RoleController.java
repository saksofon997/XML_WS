package agent.controller.user;

import agent.dto.user.RoleDTO;
import agent.exceptions.ConversionFailedError;
import agent.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "role")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ACTIVATE_USER_PERMISSION') or hasAuthority('DEACTIVATE_USER_PERMISSION')")
    public ResponseEntity<List<RoleDTO>> getAll() throws ConversionFailedError {

        List<RoleDTO> roles = roleService.getAll();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
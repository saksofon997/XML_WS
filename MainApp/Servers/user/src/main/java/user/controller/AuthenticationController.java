package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import user.dto.UserDTO;
import user.exceptions.ConversionFailedError;
import user.exceptions.DuplicateEntity;
import user.exceptions.InvalidEmailOrPasswordError;
import user.model.User;
import user.repository.UserRepository;
import user.security.JwtAuthenticationRequest;
import user.security.TokenUtils;
import user.service.AuthUserDetailsService;
import user.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    TokenUtils tokenUtils;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws AuthenticationException, IOException {

        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        } catch (Exception e){
            return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);
        }

        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token
        //User user = (User) authentication.getPrincipal();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(loggedInUser.getName());

        String jwt = tokenUtils.generateToken(user.getEmail());
        // Izbaceno (za sad?)
        int expiresIn = tokenUtils.getExpiredIn();

        UserState userState = new UserState();
        userState.token = jwt;
        userState.user = new UserDTO(user);

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(userState);
    }

    @RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> verify(@PathVariable("token") String token){
        String email = tokenUtils.getEmailFromToken(token);
        if (email == null){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        UserDetails user = authUserDetailsService.loadUserByUsername(email);
        if (user == null){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        if (tokenUtils.validateToken(token, user)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createNew(@RequestBody UserDTO userDTO) throws DuplicateEntity, InvalidEmailOrPasswordError, ConversionFailedError {

        UserDTO added = userService.add(userDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }


    static class UserState {
        public String token;
        public UserDTO user;
    }
}

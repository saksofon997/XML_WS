package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import user.exceptions.ConversionFailedError;
import user.exceptions.DuplicateEntity;
import user.model.Company;
import user.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATION')")
    public ResponseEntity<List<Company>> getAll() {
        return new ResponseEntity<>(companyService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATION')")
    public ResponseEntity<Company> create(@RequestBody Company company) throws ConversionFailedError, DuplicateEntity {
        return new ResponseEntity<>(companyService.add(company), HttpStatus.CREATED);
    }
}

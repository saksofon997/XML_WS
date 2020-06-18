package agent.controller.vehicle;

import agent.dto.shared.CategoryDTO;
import agent.dto.vehicle.CategoryPageDTO;
import agent.exceptions.ConversionFailedError;
import agent.exceptions.DuplicateEntity;
import agent.exceptions.EntityNotFound;
import agent.service.vehicle.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@RequestHeader(value = "page", required = false) Integer pageNo,
                                    @RequestHeader(value = "sort", required = false) String sort,
                                    @RequestHeader(value = "pageable", required = false) Boolean pageable)
            throws ConversionFailedError, EntityNotFound {
        sort = (sort != null) ? sort : "id";
        pageNo = (pageNo != null) ? pageNo : 0;
        if (pageable) {
            CategoryPageDTO categories = categoryService.getAllPageable(pageNo, sort);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            List<CategoryDTO> allCategories = categoryService.getAll();
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<CategoryDTO> createNew(@RequestBody CategoryDTO categoryDTO) throws ConversionFailedError, DuplicateEntity {

        CategoryDTO added = categoryService.add(categoryDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getOne(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        CategoryDTO categoryDTO = categoryService.getOne(id);

        return new ResponseEntity<>(categoryDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CHANGE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,
                                              @RequestBody CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError {

        CategoryDTO updated = categoryService.update(id, categoryDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DELETE_VEHICLE_PART_PERMISSION')")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        categoryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

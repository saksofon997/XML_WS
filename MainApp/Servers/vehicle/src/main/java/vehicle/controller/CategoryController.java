package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saga.dto.BrandDTO;
import saga.dto.CategoryDTO;
import vehicle.dto.CategoryPageDTO;
import vehicle.exceptions.ConversionFailedError;
import vehicle.exceptions.DuplicateEntity;
import vehicle.exceptions.EntityNotFound;
import vehicle.model.Category;
import vehicle.service.CategoryService;

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
        sort = (sort != null) ? sort: "id";
        pageNo = (pageNo != null) ? pageNo: 0;
        if (pageable){
            CategoryPageDTO categories = categoryService.getAllPageable(pageNo, sort);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            List<CategoryDTO> allCategories = categoryService.getAll();
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,
                                         @RequestBody CategoryDTO categoryDTO) throws EntityNotFound, ConversionFailedError {

        CategoryDTO updated = categoryService.update(id, categoryDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) throws ConversionFailedError, EntityNotFound {

        categoryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

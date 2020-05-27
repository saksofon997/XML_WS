package vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.dto.CategoryDTO;
import vehicle.model.Category;
import vehicle.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getAll() {

        List<CategoryDTO> categories = categoryService.getAll();

        return new ResponseEntity<>(categories, HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> createNew(@RequestBody CategoryDTO categoryDTO) {

        CategoryDTO added = categoryService.add(categoryDTO);

        return new ResponseEntity<>(added, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getOne(@PathVariable Long id) {

        CategoryDTO categoryDTO = categoryService.getOne(id);

        return new ResponseEntity<>(categoryDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,
                                         @RequestBody CategoryDTO categoryDTO) {

        CategoryDTO updated = categoryService.update(id, categoryDTO);

        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}",
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {

        CategoryDTO deleted = categoryService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.ACCEPTED);
    }
}

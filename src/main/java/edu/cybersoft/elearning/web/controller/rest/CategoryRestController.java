package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.CategoryDto;
import edu.cybersoft.elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "/api/categories" })
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = { "" })
    public Object addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            this.categoryService.add(categoryDto);
            return new ResponseEntity<String>("Successfully added new category.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getCategories() {
        try {
            List<CategoryDto> categoryDtos = this.categoryService.findAll();
            return new ResponseEntity<Object>(categoryDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No categories exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getCategory(@PathVariable("id") Long id) {
        try {
            CategoryDto categoryDto = this.categoryService.findById(id);
            return new ResponseEntity<Object>(categoryDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateCategory(@RequestBody CategoryDto categoryDto) {
        try {
            this.categoryService.update(categoryDto);
            return new ResponseEntity<String>("Successfully updated category!", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeCategory(@PathVariable("id") Long id) {
        try {
            this.categoryService.delete(id);
            return new ResponseEntity<String>("Category removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
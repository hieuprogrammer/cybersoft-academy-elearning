package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/categories" })
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = { "" })
    public String getCategories() {
        return "Adminity UI/category-index";
    }

    @PostMapping(path = { "" })
    public String addCategory(@RequestBody Category category) {
        return "Adminity UI/category-add";
    }
}
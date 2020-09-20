package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.CategoryDto;
import edu.cybersoft.elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/categories" })
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = {""})
    public String getCategories(Model model) {
        model.addAttribute("categories", this.categoryService.findAll());
        return "Adminity UI/category/category-index";
    }

    @GetMapping(path = {"/add"})
    public String addCategory(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "Adminity UI/category/category-add";
    }

    @PostMapping(path = {"/add"})
    public String addCategory(Model model, @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/category/category-add";
        }
        try {
            this.categoryService.add(categoryDto);
            return "redirect:/categories";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Thêm mới thất bại!");
        return "Adminity UI/category/category-add";
    }

    @GetMapping(path = {"/update"})
    public String updateCategory(@RequestParam("id") Long id, Model model) {
        model.addAttribute("categoryDto", this.categoryService.findById(id));
        return "Adminity UI/category/category-update";
    }

    @PostMapping(path = {"/update"})
    public String updateCategory(Model model, @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/category/category-update";
        }

        try {
            this.categoryService.update(categoryDto);
            return "redirect:/categories";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "categories/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteCategory(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return "redirect:/categories";
    }
}
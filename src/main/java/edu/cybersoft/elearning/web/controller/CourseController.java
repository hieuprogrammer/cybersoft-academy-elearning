package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.CourseDto;
import edu.cybersoft.elearning.service.CategoryService;
import edu.cybersoft.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/courses" })
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    @Autowired
    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping(path = { "" })
    public String getCourses(Model model) {
        model.addAttribute("courses", this.courseService.findAll());
        return "Adminity UI/course/course-list";
    }

    @GetMapping(path = {"/add"})
    public String addCourse(Model model) {
        model.addAttribute("courseDto", new CourseDto());
        model.addAttribute("categories", this.categoryService.findAll());
        return "Adminity UI/course/course-add";
    }

    @PostMapping(path = {"/add"})
    public String addCourse(Model model, @ModelAttribute("courseDto") CourseDto courseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/course/course-add";
        }

        try {
            this.courseService.add(courseDto);
            return "redirect:/courses";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "Adminity UI/course/course-add";
    }

    @GetMapping(path = {"/update"})
    public String updateCourse(@RequestParam("id") Long id, Model model) {
        model.addAttribute("courseDto", this.courseService.findById(id));
        model.addAttribute("categories", this.categoryService.findAll());
        return "Adminity UI/course/course-update";
    }

    @PostMapping(path = {"/update"})
    public String updateCourse(Model model, @ModelAttribute("courseDto") CourseDto courseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/course/course-update";
        }

        try {
            this.courseService.update(courseDto);
            return "redirect:/courses";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "courses/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteCourse(@PathVariable("id") Long id) {
        this.courseService.delete(id);
        return "redirect:/courses";
    }
}
package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.TargetDto;
import edu.cybersoft.elearning.service.CourseService;
import edu.cybersoft.elearning.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = {"/targets"})
public class TargetController {
    private final TargetService targetService;
    private final CourseService courseService;

    @Autowired
    public TargetController(TargetService targetService, CourseService courseService) {
        this.targetService = targetService;
        this.courseService = courseService;
    }

    @GetMapping(path = {""})
    public String getTargets(Model model) {
        model.addAttribute("targets", this.targetService.findAll());
        return "Adminity UI/target/target-list";
    }

    @GetMapping(path = {"/add"})
    public String addTarget(Model model) {
        model.addAttribute("targetDto", new TargetDto());
        model.addAttribute("courses", this.courseService.findAll());
        return "Adminity UI/target/target-add";
    }

    @PostMapping(path = {"/add"})
    public String addTarget(Model model, @ModelAttribute("targetDto") TargetDto targetDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/target/target-add";
        }
        try {
            this.targetService.add(targetDto);
            return "redirect:/targets";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "Adminity UI/target/target-add";
    }

    @GetMapping(path = {"/update"})
    public String updateTarget(@RequestParam("id") Long id, Model model) {
        model.addAttribute("targetDto", this.targetService.findById(id));
        model.addAttribute("courses", this.courseService.findAll());
        return "Adminity UI/target/target-update";
    }

    @PostMapping(path = {"/update"})
    public String updateTarget(Model model, @ModelAttribute("userDto") TargetDto targetDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/target/target-update";
        }

        try {
            this.targetService.update(targetDto);
            return "redirect:/targets";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "targets/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteTarget(@PathVariable("id") Long id) {
        this.targetService.delete(id);
        return "redirect:/targets";
    }
}
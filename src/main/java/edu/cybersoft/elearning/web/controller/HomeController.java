package edu.cybersoft.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/" })
public class HomeController {
    @GetMapping(path = { "" })
    public String home() {
        return "Adminity UI/course-list";
    }

    @GetMapping(path = { "swagger" })
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/users" })
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = { "" })
    public String getUsers() {
        return "Adminity UI/user-index";
    }

    @PostMapping(path = { "" })
    public String addTarget(@RequestBody User user) {
        return "Adminity UI/user-add";
    }
}
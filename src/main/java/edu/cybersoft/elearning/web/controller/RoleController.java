package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.domain.model.Role;
import edu.cybersoft.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/roles" })
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = { "" })
    public String getRoles() {
        return "Adminity UI/role-index";
    }

    @PostMapping(path = { "" })
    public String addRole(@RequestBody Role role) {
        return "Adminity UI/role-add";
    }
}
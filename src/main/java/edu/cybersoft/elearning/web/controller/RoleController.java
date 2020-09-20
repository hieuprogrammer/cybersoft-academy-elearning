package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.RoleDto;
import edu.cybersoft.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = {"/roles"})
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = {""})
    public String getRoles(Model model) {
        List<RoleDto> roles = this.roleService.findAll();
        model.addAttribute("roles", roles);
        return "Adminity UI/role/role-index";
    }

    @GetMapping(path = {"/add"})
    public String addRole(Model model) {
        model.addAttribute("roleDto", new RoleDto());
        return "Adminity UI/role/role-add";
    }

    @PostMapping(path = {"/add"})
    public String addRole(Model model, @ModelAttribute("roleDto") RoleDto roleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/role/role-add";
        }
        try {
            this.roleService.add(roleDto);
            return "redirect:/roles";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Thêm mới thất bại!");
        return "Adminity UI/role/role-add";
    }

    @GetMapping(path = {"/update"})
    public String updateRole(@RequestParam("id") Long id, Model model) {
        model.addAttribute("roleDto", this.roleService.findById(id));
        return "Adminity UI/role/role-update";
    }

    @PostMapping(path = {"/update"})
    public String updateRole(Model model, @ModelAttribute("roleDto") RoleDto roleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/role/role-update";
        }

        try {
            this.roleService.update(roleDto);
            return "redirect:/roles";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "roles/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteRole(@PathVariable("id") Long id) {
        this.roleService.delete(id);
        return "redirect:/roles";
    }
}
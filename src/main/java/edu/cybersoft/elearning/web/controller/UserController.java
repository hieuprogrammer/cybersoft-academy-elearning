package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.UserDto;
import edu.cybersoft.elearning.service.RoleService;
import edu.cybersoft.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = {"/users"})
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(path = {""})
    public String getUsers(Model model) {
        List<UserDto> users = this.userService.findAll();
        model.addAttribute("users", users);
        return "Adminity UI/user/user-index";
    }

    @GetMapping(path = {"/add"})
    public String addUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", this.roleService.findAll());
        return "Adminity UI/user/user-add";
    }

    @PostMapping(path = {"/add"})
    public String addUser(Model model, @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/user/user-add";
        }
        try {
            this.userService.add(userDto);
            return "redirect:/users";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Thêm mới thất bại!");
        return "Adminity UI/user/user-add";
    }

    @GetMapping(path = {"/update"})
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("userDto", this.userService.findById(id));
        model.addAttribute("roles", this.roleService.findAll());
        return "Adminity UI/user/user-update";
    }

    @PostMapping(path = {"/update"})
    public String updateUser(Model model, @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/user/user-update";
        }

        try {
            this.userService.update(userDto);
            return "redirect:/users";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "users/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return "redirect:/users";
    }
}
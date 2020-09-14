package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.UserDto;
import edu.cybersoft.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "api/users" })
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = { "" })
    public Object addUser(@RequestBody UserDto userDto) {
        try {
            this.userService.add(userDto);
            return new ResponseEntity<String>("Successfully added new user.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getUsers() {
        try {
            List<UserDto> userDtos = this.userService.findAll();
            return new ResponseEntity<Object>(userDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No users exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getUser(@PathVariable("id") Long id) {
        try {
            UserDto userDto = this.userService.findById(id);
            return new ResponseEntity<Object>(userDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateUser(@RequestBody UserDto userDto) {
        try {
            this.userService.update(userDto);
            return new ResponseEntity<String>("Successfully updated user.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeUser(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return new ResponseEntity<String>("User removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.RoleDto;
import edu.cybersoft.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "/api/roles" })
public class RoleRestController {
    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = { "" })
    public Object addRole(@RequestBody RoleDto roleDto) {
        try {
            this.roleService.add(roleDto);
            return new ResponseEntity<String>("Successfully added new role.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new course. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getRoles() {
        try {
            List<RoleDto> roleDtos = this.roleService.findAll();
            return new ResponseEntity<Object>(roleDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No roles exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getRole(@PathVariable("id") Long id) {
        try {
            RoleDto roleDto = this.roleService.findById(id);
            return new ResponseEntity<Object>(roleDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateRole(@RequestBody RoleDto roleDto) {
        try {
            this.roleService.update(roleDto);
            return new ResponseEntity<String>("Successfully updated role.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeRole(@PathVariable("id") Long id) {
        try {
            this.roleService.delete(id);
            return new ResponseEntity<String>("Role removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
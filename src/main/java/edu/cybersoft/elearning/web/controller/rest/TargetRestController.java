package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.TargetDto;
import edu.cybersoft.elearning.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "api/targets" })
public class TargetRestController {
    private final TargetService targetService;

    @Autowired
    public TargetRestController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping(path = { "" })
    public Object addTarget(@RequestBody TargetDto targetDto) {
        try {
            this.targetService.add(targetDto);
            return new ResponseEntity<String>("Successfully added new target.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getTargets() {
        try {
            List<TargetDto> targetDtos = this.targetService.findAll();
            return new ResponseEntity<Object>(targetDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No targets exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getTarget(@PathVariable("id") Long id) {
        try {
            TargetDto targetDto = this.targetService.findById(id);
            return new ResponseEntity<Object>(targetDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Target not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateTarget(@RequestBody TargetDto targetDto) {
        try {
            this.targetService.update(targetDto);
            return new ResponseEntity<String>("Successfully updated target.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeTarget(@PathVariable("id") Long id) {
        try {
            this.targetService.delete(id);
            return new ResponseEntity<String>("Target removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
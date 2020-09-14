package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.CourseDto;
import edu.cybersoft.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "/api/courses" })
public class CourseRestController {
    private final CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(path = { "" })
    public Object addCourse(@RequestBody CourseDto courseDto) {
        try {
            this.courseService.add(courseDto);
            return new ResponseEntity<String>("Successfully added new course.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new course. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getCourses() {
        try {
            List<CourseDto> courseDtos = this.courseService.findAll();
            return new ResponseEntity<Object>(courseDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No courses exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getCourse(@PathVariable("id") Long id) {
        try {
            CourseDto courseDto = this.courseService.findById(id);
            return new ResponseEntity<Object>(courseDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Course not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateCourse(@RequestBody CourseDto courseDto) {
        try {
            this.courseService.update(courseDto);
            return new ResponseEntity<String>("Successfully updated course.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeCourse(@PathVariable("id") Long id) {
        try {
            this.courseService.delete(id);
            return new ResponseEntity<String>("Course removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
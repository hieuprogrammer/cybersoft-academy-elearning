package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/courses" })
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = { "" })
    public String getCourses() {
        return "Adminity UI/course-list";
    }

    @PostMapping(path = { "" })
    public String addCourse(@RequestBody Course course) {
        return "Adminity UI/course-add";
    }
}
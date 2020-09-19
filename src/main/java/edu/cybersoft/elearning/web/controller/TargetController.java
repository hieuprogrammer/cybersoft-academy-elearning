package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.domain.model.Target;
import edu.cybersoft.elearning.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/targets" })
public class TargetController {
    private final TargetService targetService;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping(path = { "" })
    public String getTargets() {
        return "Adminity UI/target-list";
    }

    @PostMapping(path = { "" })
    public String addTarget(@RequestBody Target target) {
        return "Adminity UI/target-add";
    }
}
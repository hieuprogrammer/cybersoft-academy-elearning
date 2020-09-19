package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.domain.model.Video;
import edu.cybersoft.elearning.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/videos" })
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping(path = { "" })
    public String getVideos() {
        return "Adminity UI/video-list";
    }

    @PostMapping(path = { "" })
    public String addVideo(@RequestBody Video video) {
        return "Adminity UI/video-add";
    }
}
package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.VideoDto;
import edu.cybersoft.elearning.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "api/videos" })
public class VideoRestController {
    private final VideoService videoService;

    @Autowired
    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(path = { "" })
    public Object addVideo(@RequestBody VideoDto videoDto) {
        try {
            this.videoService.add(videoDto);
            return new ResponseEntity<String>("Successfully added new video.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getVideos() {
        try {
            List<VideoDto> videoDtos = this.videoService.findAll();
            return new ResponseEntity<Object>(videoDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No videos exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getVideo(@PathVariable("id") Long id) {
        try {
            VideoDto videoDto = this.videoService.findById(id);
            return new ResponseEntity<Object>(videoDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Video not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateVideo(@RequestBody VideoDto videoDto) {
        try {
            this.videoService.update(videoDto);
            return new ResponseEntity<String>("Successfully updated video.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeVideo(@PathVariable("id") Long id) {
        try {
            this.videoService.delete(id);
            return new ResponseEntity<String>("Video removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
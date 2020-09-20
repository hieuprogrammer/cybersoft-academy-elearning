package edu.cybersoft.elearning.web.controller;

import edu.cybersoft.elearning.dto.model.VideoDto;
import edu.cybersoft.elearning.service.CourseService;
import edu.cybersoft.elearning.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = { "/videos" })
public class VideoController {
    private final VideoService videoService;
    private final CourseService courseService;

    @Autowired
    public VideoController(VideoService videoService, CourseService courseService) {
        this.videoService = videoService;
        this.courseService = courseService;
    }

    @GetMapping(path = { "" })
    public String getVideos(Model model) {
        model.addAttribute("videos", this.videoService.findAll());
        return "Adminity UI/video/video-list";
    }

    @GetMapping(path = {"/add"})
    public String addVideo(Model model) {
        model.addAttribute("videoDto", new VideoDto());
        model.addAttribute("courses", this.courseService.findAll());
        return "Adminity UI/video/video-add";
    }

    @PostMapping(path = {"/add"})
    public String addVideo(Model model, @ModelAttribute("videoDto") VideoDto videoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/video/video-add";
        }
        try {
            this.videoService.add(videoDto);
            return "redirect:/videos";
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Thêm mới thất bại!");
        return "Adminity UI/video/video-add";
    }

    @GetMapping(path = {"/update"})
    public String updateVideo(@RequestParam("id") Long id, Model model) {
        model.addAttribute("videoDto", this.videoService.findById(id));
        model.addAttribute("courses", this.courseService.findAll());
        return "Adminity UI/video/video-update";
    }

    @PostMapping(path = {"/update"})
    public String updateVideo(Model model, @ModelAttribute("videoDto") VideoDto videoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Adminity UI/video/video-update";
        }

        try {
            this.videoService.update(videoDto);
            return "redirect:/videos";
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        model.addAttribute("errorMessage", "Cập nhật thất bại!");
        return "videos/update";
    }

    @GetMapping(path = {"/delete/{id}"})
    public String deleteVideo(@PathVariable("id") Long id) {
        this.videoService.delete(id);
        return "redirect:/videos";
    }
}
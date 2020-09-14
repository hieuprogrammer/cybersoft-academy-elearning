package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.VideoDto;

import java.util.List;

public interface VideoService {
    void add(VideoDto videoDto);

    List<VideoDto> findAll();

    VideoDto findById(Long id);

    void update(VideoDto videoDto);

    void delete(Long id);
}
package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.CourseDto;

import java.util.List;

public interface CourseService {
    void add(CourseDto courseDto);

    List<CourseDto> findAll();

    CourseDto findById(Long id);

    void update(CourseDto courseDto);

    void delete(Long id);
}
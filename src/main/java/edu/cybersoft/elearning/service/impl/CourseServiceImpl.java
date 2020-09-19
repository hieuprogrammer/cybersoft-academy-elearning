package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.dto.mapper.CourseMapper;
import edu.cybersoft.elearning.dto.model.CourseDto;
import edu.cybersoft.elearning.repository.CourseRepository;
import edu.cybersoft.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void add(CourseDto courseDto) {
        this.courseRepository.save(CourseMapper.toCourse(courseDto));
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = this.courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<CourseDto>();
        for (Course course : courses) {
            courseDtos.add(CourseMapper.toCourseDto(course));
        }
        return courseDtos;
    }

    @Override
    public CourseDto findById(Long id) {
        return CourseMapper.toCourseDto(this.courseRepository.findById(id).get());
    }

    @Override
    public void update(CourseDto courseDto) {
        Course course = this.courseRepository.findById(courseDto.getId()).get();
        course.setTitle(courseDto.getTitle());
        course.setImage(courseDto.getImage());
        course.setLecturesCount(courseDto.getLecturesCount());
        course.setHourCount(courseDto.getHourCount());
        course.setViewCount(courseDto.getViewCount());
        course.setPrice(courseDto.getPrice());
        course.setDiscount(course.getDiscount());
        course.setPromotionPrice(courseDto.getPromotionPrice());
        course.setDescription(courseDto.getDescription());
        course.setContent(courseDto.getContent());
        course.setCategory(new Category(courseDto.getCategoryId()));
        course.setLastUpdate(courseDto.getLastUpdate());
        course.setUsers(courseDto.getUsers());
        course.setTargets(courseDto.getTargets());
        course.setVideos(courseDto.getVideos());
        this.courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        this.courseRepository.deleteById(id);
    }
}
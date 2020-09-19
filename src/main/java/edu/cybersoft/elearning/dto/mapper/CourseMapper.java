package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.domain.model.Course;
import edu.cybersoft.elearning.dto.model.CourseDto;

public class CourseMapper {
    public static CourseDto toCourseDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getImage(),
                course.getLecturesCount(),
                course.getHourCount(),
                course.getViewCount(),
                course.getPrice(),
                course.getDiscount(),
                course.getPromotionPrice(),
                course.getDescription(),
                course.getContent(),
                course.getCategory().getId(),
                course.getLastUpdate(),
                course.getUsers(),
                course.getTargets(),
                course.getVideos());
    }

    public static Course toCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
        course.setImage(courseDto.getImage());
        course.setLecturesCount(courseDto.getLecturesCount());
        course.setHourCount(courseDto.getHourCount());
        course.setViewCount(courseDto.getViewCount());
        course.setPrice(courseDto.getPrice());
        course.setDiscount(courseDto.getDiscount());
        course.setPromotionPrice(courseDto.getPromotionPrice());
        course.setDescription(courseDto.getDescription());
        course.setContent(courseDto.getContent());
        course.setCategory(new Category(courseDto.getCategoryId()));
        course.setLastUpdate(courseDto.getLastUpdate());
        course.setUsers(courseDto.getUsers());
        course.setTargets(courseDto.getTargets());
        course.setVideos(courseDto.getVideos());
        return course;
    }
}
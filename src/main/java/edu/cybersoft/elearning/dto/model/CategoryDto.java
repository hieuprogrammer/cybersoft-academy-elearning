package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import edu.cybersoft.elearning.domain.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto extends BaseEntity {
    private String title;

    private String icon;

    private List<Course> courses = new ArrayList<Course>();

    public CategoryDto(Long id, String title, String icon, List<Course> courses) {
        super(id);
        this.title = title;
        this.icon = icon;
        this.courses = courses;
    }
}
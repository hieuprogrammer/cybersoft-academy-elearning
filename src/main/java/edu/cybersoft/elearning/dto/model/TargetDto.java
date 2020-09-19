package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TargetDto extends BaseEntity {
    private String title;

    private Long courseId;

    public TargetDto(Long id, String title, Long courseId) {
        super(id);
        this.title = title;
        this.courseId = courseId;
    }
}
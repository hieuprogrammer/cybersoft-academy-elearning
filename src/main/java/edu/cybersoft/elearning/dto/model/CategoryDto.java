package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto extends BaseEntity {
    private String title;

    private String icon;

    public CategoryDto(Long id, String title, String icon) {
        super(id);
        this.title = title;
        this.icon = icon;
    }
}
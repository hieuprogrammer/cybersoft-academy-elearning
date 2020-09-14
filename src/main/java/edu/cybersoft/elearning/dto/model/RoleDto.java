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
public class RoleDto extends BaseEntity {
    private String name;

    private String description;

    public RoleDto(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
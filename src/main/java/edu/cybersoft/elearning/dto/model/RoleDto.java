package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import edu.cybersoft.elearning.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseEntity {
    private String name;

    private String description;

    private List<User> users = new ArrayList<User>();

    public RoleDto(Long id, String name, String description, List<User> users) {
        super(id);
        this.name = name;
        this.description = description;
        this.users = users;
    }
}
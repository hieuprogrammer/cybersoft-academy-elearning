package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import edu.cybersoft.elearning.domain.model.Course;
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
public class UserDto extends BaseEntity {
    private String email;

    private String password;

    private String fullName;

    private String avatar;

    private String phone;

    private String address;

    private Long roleId;

    private List<Course> courses = new ArrayList<Course>();

    public UserDto(Long id, String email, String password, String fullName, String avatar, String phone, String address, Long roleId, List<Course> courses) {
        super(id);
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.courses = courses;
    }
}
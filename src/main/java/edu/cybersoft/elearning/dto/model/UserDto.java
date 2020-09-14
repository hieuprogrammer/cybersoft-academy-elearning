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
public class UserDto extends BaseEntity {
    private String email;

    private String password;

    private String fullName;

    private String avatar;

    private String phone;

    private String address;

    private String roleName;

    private Long roleId;

    private String roleDescription;

    public UserDto(Long id, String email, String password, String fullName, String avatar, String phone, String address, String roleName, Long roleId, String roleDescription) {
        super(id);
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.roleName = roleName;
        this.roleId = roleId;
        this.roleDescription = roleDescription;
    }
}
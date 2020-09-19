package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Role;
import edu.cybersoft.elearning.dto.model.RoleDto;

public class RoleMapper {
    public static RoleDto toRoleDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getUsers()
        );
    }

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setUsers(roleDto.getUsers());
        return role;
    }
}
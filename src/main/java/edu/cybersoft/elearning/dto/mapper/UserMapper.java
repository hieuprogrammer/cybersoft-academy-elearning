package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.dto.model.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getAvatar(),
                user.getPhone(),
                user.getAddress(),
                user.getRole().getName(),
                user.getRole().getId(),
                user.getRole().getDescription()
        );
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setAvatar(userDto.getAvatar());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        return user;
    }
}
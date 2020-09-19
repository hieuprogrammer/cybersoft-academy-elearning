package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Role;
import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.dto.model.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {
    @Bean
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getAvatar(),
                user.getPhone(),
                user.getAddress(),
                user.getRole().getId(),
                user.getCourses()
        );
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
        user.setFullName(userDto.getFullName());
        user.setAvatar(userDto.getAvatar());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setRole(new Role(userDto.getRoleId()));
        user.setCourses(userDto.getCourses());
        return user;
    }
}
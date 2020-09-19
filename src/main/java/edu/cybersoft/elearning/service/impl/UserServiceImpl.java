package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Role;
import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.dto.mapper.UserMapper;
import edu.cybersoft.elearning.dto.model.UserDto;
import edu.cybersoft.elearning.repository.UserRepository;
import edu.cybersoft.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void add(UserDto userDto) {
        this.userRepository.save(UserMapper.toUser(userDto));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User user : users) {
            userDtos.add(UserMapper.toUserDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.toUserDto(this.userRepository.findById(id).get());
    }

    @Override
    public void update(UserDto userDto) {
        User user = this.userRepository.findById(userDto.getId()).get();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
        user.setFullName(userDto.getFullName());
        user.setAvatar(userDto.getAvatar());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setRole(new Role(userDto.getRoleId()));
        this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> search(String keyword) {
        List<UserDto> dtos = new ArrayList<UserDto>();

        List<User> users = userRepository.search(keyword);
        for (User user : users) {
            dtos.add(UserMapper.toUserDto(user));
        }
        return dtos;
    }
}
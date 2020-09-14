package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.UserDto;

import java.util.List;

public interface UserService {
    void add(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(Long id);

    void update(UserDto userDto);

    void delete(Long id);

    List<UserDto> search(String keyword);
}
package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.RoleDto;

import java.util.List;

public interface RoleService {
    void add(RoleDto roleDto);

    List<RoleDto> findAll();

    RoleDto findById(Long id);

    void update(RoleDto roleDto);

    void delete(Long id);
}
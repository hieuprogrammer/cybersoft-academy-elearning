package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Role;
import edu.cybersoft.elearning.dto.mapper.RoleMapper;
import edu.cybersoft.elearning.dto.model.RoleDto;
import edu.cybersoft.elearning.repository.RoleRepository;
import edu.cybersoft.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(RoleDto roleDto) {
        this.roleRepository.save(RoleMapper.toRole(roleDto));
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<RoleDto>();
        for (Role role : roles) {
            roleDtos.add(RoleMapper.toRoleDto(role));
        }
        return roleDtos;
    }

    @Override
    public RoleDto findById(Long id) {
        return RoleMapper.toRoleDto(this.roleRepository.findById(id).get());
    }

    @Override
    public void update(RoleDto roleDto) {
        Role role = this.roleRepository.findById(roleDto.getId()).get();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        this.roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        this.roleRepository.deleteById(id);
    }
}
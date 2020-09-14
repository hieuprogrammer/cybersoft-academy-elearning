package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.TargetDto;

import java.util.List;

public interface TargetService {
    void add(TargetDto targetDto);

    List<TargetDto> findAll();

    TargetDto findById(Long id);

    void update(TargetDto targetDto);

    void delete(Long id);
}
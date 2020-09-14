package edu.cybersoft.elearning.service;

import edu.cybersoft.elearning.dto.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    void add(CategoryDto categoryDto);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    void update(CategoryDto categoryDto);

    void delete(Long id);
}
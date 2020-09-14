package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.dto.mapper.CategoryMapper;
import edu.cybersoft.elearning.dto.model.CategoryDto;
import edu.cybersoft.elearning.repo.CategoryRepository;
import edu.cybersoft.elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(CategoryDto categoryDto) {
        this.categoryRepository.save(CategoryMapper.toCategory(categoryDto));
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
        for (Category category : categories) {
            categoryDtos.add(CategoryMapper.toCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto findById(Long id) {
        return CategoryMapper.toCategoryDto(this.categoryRepository.findById(id).get());
    }

    @Override
    public void update(CategoryDto categoryDto) {
        Category category = this.categoryRepository.findById(categoryDto.getId()).get();
        category.setTitle(categoryDto.getTitle());
        category.setIcon(categoryDto.getIcon());
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
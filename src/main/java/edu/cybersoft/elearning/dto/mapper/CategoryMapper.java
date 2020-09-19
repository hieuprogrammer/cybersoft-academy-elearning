package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Category;
import edu.cybersoft.elearning.dto.model.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getTitle(),
                category.getIcon(),
                category.getCourses());
    }

    public static Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setIcon(categoryDto.getIcon());
        category.setCourses(categoryDto.getCourses());
        return category;
    }
}
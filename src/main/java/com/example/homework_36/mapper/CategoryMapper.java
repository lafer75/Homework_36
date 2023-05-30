package com.example.homework_36.mapper;

import com.example.homework_36.dto.CategoryDto;
import com.example.homework_36.entity.Category;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    ConvertTime convertTime = new ConvertTime();
    public CategoryDto mapCategoryToCategoryDto(Category category) {

        return CategoryDto.builder()
                .name(category.getName())
                .createdAt(convertTime.longToString(category.getCreatedAt()))
                .build();
    }
    public List<CategoryDto> mapListCategoryToListCategoryDto(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category c : categories) {
            categoryDtos.add(mapCategoryToCategoryDto(c));
        }
        return categoryDtos;
    }
}

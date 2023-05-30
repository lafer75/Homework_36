package com.example.homework_36.controller;


import com.example.homework_36.dto.CategoryDto;
import com.example.homework_36.entity.Category;
import com.example.homework_36.mapper.CategoryMapper;
import com.example.homework_36.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

    @RestController
    @RequestMapping("/category")
    @RequiredArgsConstructor
    public class CategoryController {

        private final CategoryService categoryService;

        private final CategoryMapper categoryMapper;

        @GetMapping("/all")
        public List<CategoryDto> findAll() {
            return this.categoryMapper.mapListCategoryToListCategoryDto(categoryService.findALL());
        }

        @GetMapping("/find_by_name")
        public List<CategoryDto> findByName(@RequestParam("text") String text) {
            return this.categoryMapper.mapListCategoryToListCategoryDto(categoryService.findByName(text));
        }

        @PostMapping("/add")
        @ResponseStatus(HttpStatus.CREATED)
        public Category add(@RequestParam("name") String name) {

            Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

            return categoryService.save(new Category(name, dateAddNote));
        }

        @GetMapping("/update")
        public void updateById(@RequestParam("id") Integer id,
                               @RequestParam("name") String name) {
            categoryService.update(id, name);
        }
        @GetMapping("/delete")
        public void deleteById(@RequestParam("id") Integer id) {
            categoryService.deleteById(id);
        }
    }


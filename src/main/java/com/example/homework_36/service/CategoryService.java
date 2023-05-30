package com.example.homework_36.service;

import com.example.homework_36.entity.Category;
import com.example.homework_36.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> findALL() {
        return this.categoryRepository.findAll();
    }
    public List<Category> findByName(String text) {
        return this.categoryRepository.findNameContaining(text);
    }
    public Category save(Category category){
        return this.categoryRepository.save(category);
    }
    public void update(Integer id, String name) {
        if (this.categoryRepository.existsById(id)) {
            this.categoryRepository.updateById(id, name);
        }
    }
    public void deleteById(Integer id) {
        this.categoryRepository.deleteById(id);
    }
}
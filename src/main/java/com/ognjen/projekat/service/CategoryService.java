package com.ognjen.projekat.service;

import com.ognjen.projekat.mapper.CategoryMapper;
import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.repository.entity.CategoryEntity;
import com.ognjen.projekat.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;


    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer categoryId) {
        return mapper.toDomain(getCategoryById(categoryId));
    }

    @Transactional
    public Category create(Category category) {

        var categoryEntity = mapper.toEntity(category);

        var saved = categoryRepository.save(categoryEntity);

        return mapper.toDomain(saved);
    }

    @Transactional
    public void update(CategoryEntity updatedCategoryEntity) {

        var existingCategory = getById(updatedCategoryEntity.getId());

        existingCategory.setName(updatedCategoryEntity.getName());
    }

    @Transactional
    public void delete(Integer categoryId) {
        categoryRepository.delete(getCategoryById(categoryId));
    }

    private CategoryEntity getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found with id " + categoryId));
    }
}

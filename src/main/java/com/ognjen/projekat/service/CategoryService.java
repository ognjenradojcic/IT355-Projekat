package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.exception.UsedAttributeException;
import com.ognjen.projekat.mapper.CategoryMapper;
import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.repository.CategoryRepository;
import com.ognjen.projekat.repository.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return mapper.toDomainList(categoryRepository.findAll());
    }

    public Category getById(Integer categoryId) {
        return mapper.toDomain(getCategoryEntityById(categoryId));
    }

    @Transactional
    public Category create(Category category) {

        categoryExistsByUsernameCheck(category);

        var categoryEntity = mapper.toEntity(category);

        return mapper.toDomain(categoryRepository.save(categoryEntity));
    }

    @Transactional
    public void update(Category updatedCategory) {

        categoryExistsByUsernameCheck(updatedCategory);

        var existingCategoryEntity = getCategoryEntityById(updatedCategory.getId());

        mapper.update(existingCategoryEntity, updatedCategory);
    }

    @Transactional
    public void delete(Integer categoryId) {
        categoryNotExistsByIdCheck(categoryId);

        categoryRepository.deleteById(categoryId);
    }

    private CategoryEntity getCategoryEntityById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException("Category not found with id " + categoryId));
    }

    private void categoryExistsByUsernameCheck(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new UsedAttributeException("Category already exists with name: " + category.getName());
        }
    }

    private void categoryNotExistsByIdCheck(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found with id: " + id);
        }
    }
}

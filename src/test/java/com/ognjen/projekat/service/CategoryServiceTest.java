package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.exception.UsedAttributeException;
import com.ognjen.projekat.mapper.CategoryMapper;
import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.repository.CategoryRepository;
import com.ognjen.projekat.repository.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ognjen.projekat.EntityBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Spy
    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);


    @Test
    void getAll() {

        List<CategoryEntity> categoryEntities = Collections.singletonList(categoryEntity());

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        List<Category> categories = categoryService.getAll();

        assertEquals(CATEGORY_ID, categories.get(0).getId());

    }

    @Test
    void getById() {

        when(categoryRepository.findById(1)).thenReturn(
                Optional.of(categoryEntity()));

        Category category = categoryService.getById(1);

        assertEquals(CATEGORY_ID, category.getId());

    }

    @Test
    void getByIdException() {

        when(categoryRepository.findById(10)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> categoryService.getById(10));

    }

    @Test
    void create() {

        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity());

        Category category = categoryService.create(category());

        assertEquals(CATEGORY_ID, category.getId());

    }

    @Test
    void createException() {

        when(categoryRepository.save(any(CategoryEntity.class))).thenThrow(UsedAttributeException.class);

        assertThrows(UsedAttributeException.class, () -> categoryService.create(category()));
    }

    @Test
    void delete() {

        when(categoryRepository.existsById(USER_ID)).thenReturn(true);
        categoryService.delete(USER_ID);
        verify(categoryRepository).deleteById(USER_ID);

    }

    @Test
    void deleteException() {
        doThrow(NotFoundException.class).when(categoryRepository).existsById(CATEGORY_ID);

        assertThrows(NotFoundException.class, () -> categoryService.delete(CATEGORY_ID));
    }

    @Test
    void update() {
    }

}
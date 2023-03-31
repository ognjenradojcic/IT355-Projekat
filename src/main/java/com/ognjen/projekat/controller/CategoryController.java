package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.dto.mapper.CategoryDtoMapper;
import com.ognjen.projekat.controller.dto.request.CategoryRequest;
import com.ognjen.projekat.controller.dto.response.CategoryResponse;
import com.ognjen.projekat.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDtoMapper mapper;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return mapper.toResponseList(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable("id") Integer id) {
        return mapper.toResponse(categoryService.getById(id));
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        return mapper.toResponse(categoryService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id){
        categoryService.delete(id);
    }

    // TODO: 31.3.2023. Add update functionality
}

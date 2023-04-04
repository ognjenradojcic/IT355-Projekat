package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.annotation.AdminAuthority;
import com.ognjen.projekat.controller.annotation.UserAuthority;
import com.ognjen.projekat.controller.dto.mapper.CategoryDtoMapper;
import com.ognjen.projekat.controller.dto.request.CategoryRequest;
import com.ognjen.projekat.controller.dto.request.CategoryUpdateRequest;
import com.ognjen.projekat.controller.dto.response.CategoryResponse;
import com.ognjen.projekat.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDtoMapper mapper;

    @GetMapping
    @UserAuthority
    public List<CategoryResponse> getAll() {
        return mapper.toResponseList(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @UserAuthority
    public CategoryResponse getById(@PathVariable("id") Integer id) {
        return mapper.toResponse(categoryService.getById(id));
    }

    @PostMapping
    @AdminAuthority
    public CategoryResponse create(@RequestBody @Valid CategoryRequest request) {
        return mapper.toResponse(categoryService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @AdminAuthority
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
    }


    @PutMapping("/{id}")
    @AdminAuthority
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid CategoryUpdateRequest request) {
        categoryService.update(mapper.toDomain(request, id));
    }
}

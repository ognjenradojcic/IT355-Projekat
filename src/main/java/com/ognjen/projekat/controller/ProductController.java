package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.annotation.AdminAuthority;
import com.ognjen.projekat.controller.dto.mapper.ProductDtoMapper;
import com.ognjen.projekat.controller.dto.request.ProductRequest;
import com.ognjen.projekat.controller.dto.request.ProductUpdateRequest;
import com.ognjen.projekat.controller.dto.response.ProductResponse;
import com.ognjen.projekat.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private final ProductService productService;
    private final ProductDtoMapper mapper;


    @GetMapping
    public List<ProductResponse> getAll() {
        return mapper.toResponseList(productService.getAll());
    }


    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") Integer id) {
        return mapper.toResponse(productService.getById(id));
    }

    @PostMapping
    @AdminAuthority
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return mapper.toResponse(productService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @AdminAuthority
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @AdminAuthority
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid ProductUpdateRequest request) {
        productService.update(mapper.toDomain(request, id));
    }
}

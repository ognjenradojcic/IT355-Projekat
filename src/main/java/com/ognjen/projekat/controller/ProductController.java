package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.dto.mapper.ProductDtoMapper;
import com.ognjen.projekat.controller.dto.request.ProductRequest;
import com.ognjen.projekat.controller.dto.response.ProductResponse;
import com.ognjen.projekat.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductDtoMapper mapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponse> getAllProduct() {
        return mapper.toResponseList(productService.getAll());
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Integer id) {
        return mapper.toResponse(productService.getById(id));
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return mapper.toResponse(productService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
    }

    // TODO: 31.3.2023. Add update functionality
}

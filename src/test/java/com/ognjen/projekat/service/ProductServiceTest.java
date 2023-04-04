package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.ProductMapper;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.ProductRepository;
import com.ognjen.projekat.repository.entity.ProductEntity;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Mock
    CategoryService categoryService;

    @Spy
    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void getAll() {

        List<ProductEntity> entityList = Collections.singletonList(productEntity());

        when(productRepository.findAll()).thenReturn(entityList);

        List<Product> products = productService.getAll();

        assertEquals(PRODUCT_ID, products.get(0).getId());

    }

    @Test
    void getById() {

        when(productRepository.findById(1)).thenReturn(
                Optional.of(productEntity()));

        Product product = productService.getById(1);

        assertEquals(PRODUCT_ID, product.getId());


    }

    @Test
    void getByIdException() {

        when(productRepository.findById(1)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productService.getById(1));


    }

    @Test
    void create() {
        when(categoryService.getById(product().getCategory().getId())).thenReturn(product().getCategory());
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity());

        Product product = productService.create(product());

        assertEquals(PRODUCT_ID, product.getId());

    }

    @Test
    void delete() {

        when(productRepository.existsById(PRODUCT_ID)).thenReturn(true);
        productService.delete(PRODUCT_ID);
        verify(productRepository).deleteById(PRODUCT_ID);

    }

    @Test
    void update() {
    }
}
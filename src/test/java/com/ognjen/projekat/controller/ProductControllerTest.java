package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.ProductDtoMapper;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.service.ProductService;
import com.ognjen.projekat.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static com.ognjen.projekat.EntityBuilder.product;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(ProductController.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    TokenService service;

    @MockBean
    ProductDtoMapper mapper;

    @Test
    void getAll() throws Exception {
        when(productService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(productService.getById(1)).thenReturn(product());

        mockMvc.perform(get("/v1/products/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {

        when(productService.create(any(Product.class))).thenReturn(product());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/products")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"S23\",\"manufacturer\":\"Samsung\",\"price\":1000, \"categoryId\":1}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/products/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/products/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"S23\",\"manufacturer\":\"Samsung\",\"price\":1000, \"categoryId\":1}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
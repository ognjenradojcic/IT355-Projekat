package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.CategoryDtoMapper;
import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.service.CategoryService;
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

import static com.ognjen.projekat.EntityBuilder.category;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(CategoryController.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @MockBean
    TokenService service;

    @MockBean
    CategoryDtoMapper mapper;


    @Test
    void getAll() throws Exception {
        when(categoryService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(categoryService.getById(1)).thenReturn(category());

        mockMvc.perform(get("/v1/categories/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        when(categoryService.create(any(Category.class))).thenReturn(category());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Phones\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/categories/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/categories/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Phones\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
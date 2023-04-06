package com.ognjen.projekat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ognjen.projekat.SecurityAwareTest;
import com.ognjen.projekat.controller.dto.mapper.UserDtoMapper;
import com.ognjen.projekat.controller.dto.request.UpdateUserRequest;
import com.ognjen.projekat.service.TokenService;
import com.ognjen.projekat.service.UserService;
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

import static com.ognjen.projekat.EntityBuilder.user;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@Import(UserController.class)
@WebMvcTest(UserController.class)
class UserControllerTest extends SecurityAwareTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    TokenService tokenService;

    @MockBean
    UserDtoMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(userService.getById(1)).thenReturn(user());

        mockMvc.perform(get("/v1/users/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserInfo() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "accessToken":"TokenTest"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        var updateUserRequest = new UpdateUserRequest(1, "Ognjen", "Radojcic", "065123123");

        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUserRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());

    }
}
package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.UserDtoMapper;
import com.ognjen.projekat.controller.dto.request.LoginRequest;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.service.AuthenticationService;
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

import static com.ognjen.projekat.EntityBuilder.tokens;
import static com.ognjen.projekat.EntityBuilder.user;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(AuthenticationController.class)
@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthenticationService authenticationService;

    @MockBean
    TokenService tokenService;

    @MockBean
    UserDtoMapper mapper;


    @Test
    void register() throws Exception {
        when(authenticationService.register(any(User.class))).thenReturn(user());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/auth/register")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"ogi123\",\"password\":\"ogi123\",\"firstName\":\"Ognjen\", \"lastName\":\"Radojcic\", \"phone\":\"065123123\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    void login() throws Exception {
        when(authenticationService.login(any(LoginRequest.class))).thenReturn(tokens());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"ogi123\",\"password\":\"ogi123\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void refresh() throws Exception {
        when(authenticationService.refreshTokens(any(String.class))).thenReturn(tokens());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/auth/refresh")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"refreshToken\":\"testToken\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
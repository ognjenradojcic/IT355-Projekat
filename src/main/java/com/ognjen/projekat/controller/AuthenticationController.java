package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.dto.mapper.UserDtoMapper;
import com.ognjen.projekat.controller.dto.request.LoginRequest;
import com.ognjen.projekat.controller.dto.request.RefreshRequest;
import com.ognjen.projekat.controller.dto.request.RegisterRequest;
import com.ognjen.projekat.controller.dto.response.LoginResponse;
import com.ognjen.projekat.controller.dto.response.UserResponse;
import com.ognjen.projekat.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserDtoMapper mapper;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        return mapper.toResponse(authenticationService.register(mapper.toDomain(request)));
    }

    @PostMapping("/login")

    public LoginResponse login(@RequestBody LoginRequest request){
        return mapper.toResponse(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody RefreshRequest request){
        return mapper.toResponse(authenticationService.refreshTokens(request.refreshToken()));
    }


}

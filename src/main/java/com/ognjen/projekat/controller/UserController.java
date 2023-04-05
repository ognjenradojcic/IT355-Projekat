package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.annotation.AdminAuthority;
import com.ognjen.projekat.controller.dto.mapper.UserDtoMapper;
import com.ognjen.projekat.controller.dto.request.RequestAccess;
import com.ognjen.projekat.controller.dto.request.UpdateUserRequest;
import com.ognjen.projekat.controller.dto.response.UserResponse;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.service.TokenService;
import com.ognjen.projekat.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final UserDtoMapper mapper;

    @GetMapping
    @AdminAuthority
    public List<UserResponse> getAll() {
        return mapper.toResponseList(userService.getAll());
    }

    @GetMapping("/{id}")
    @AdminAuthority
    public UserResponse getById(@PathVariable("id") Integer id) {
        return mapper.toResponse(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) {
        userService.delete(id, user.getId());
    }

    @PostMapping
    public UserResponse getUserInfo(@Valid @RequestBody RequestAccess request) {
        return mapper.toResponse(tokenService.getUserWithToken(request.accessToken()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateUserRequest request, @PathVariable("id") Integer id, @AuthenticationPrincipal User user) {
        userService.update(mapper.toDomain(request, id), user.getId());
    }

}

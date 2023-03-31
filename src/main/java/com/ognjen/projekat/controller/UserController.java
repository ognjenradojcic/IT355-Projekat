package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.UserDtoMapper;
import com.ognjen.projekat.controller.dto.response.UserResponse;
import com.ognjen.projekat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper mapper;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return mapper.toResponseList(userService.getAll());
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") Integer id) {
        return mapper.toResponse(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    // TODO: 31.3.2023. Add update functionality
}

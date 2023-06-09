package com.ognjen.projekat.service;

import com.ognjen.projekat.controller.dto.request.LoginRequest;
import com.ognjen.projekat.exception.LoginException;
import com.ognjen.projekat.model.Tokens;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public User register(User user) {

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userService.create(user);
    }

    public Tokens login(LoginRequest request) {

        var user = userService.getByUsername(request.username());

        validatePassword(request, user);

        return tokenService.generateTokens(user);

    }

    private void validatePassword(LoginRequest request, User user) {
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new LoginException("Username or password are incorrect");
        }
    }

    public Tokens refreshTokens(String request) {
        return tokenService.refreshTokens(request);
    }
}

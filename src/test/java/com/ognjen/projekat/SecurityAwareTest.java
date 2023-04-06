package com.ognjen.projekat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.ognjen.projekat.EntityBuilder.user;
import static org.mockito.Mockito.when;

public abstract class SecurityAwareTest {

    @Mock
    private SecurityContext context;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        when(authentication.getPrincipal()).thenReturn(user());
        when(context.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }
}

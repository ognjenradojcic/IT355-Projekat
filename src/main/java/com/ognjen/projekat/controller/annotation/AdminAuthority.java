package com.ognjen.projekat.controller.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAuthority(T(com.ognjen.projekat.model.enums.Role).ADMIN) ")
public @interface AdminAuthority {
}

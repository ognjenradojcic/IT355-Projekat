package com.ognjen.projekat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;
}

package com.ognjen.projekat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {
    private Integer id;

    private String username;

    private String password;
}

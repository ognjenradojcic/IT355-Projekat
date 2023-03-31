package com.ognjen.projekat.controller.dto.mapper;

import com.ognjen.projekat.controller.dto.request.RegisterRequest;
import com.ognjen.projekat.controller.dto.response.LoginResponse;
import com.ognjen.projekat.controller.dto.response.UserResponse;
import com.ognjen.projekat.model.Tokens;
import com.ognjen.projekat.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserDtoMapper {
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);

    User toDomain(RegisterRequest request);

    LoginResponse toResponse(Tokens tokens);
}

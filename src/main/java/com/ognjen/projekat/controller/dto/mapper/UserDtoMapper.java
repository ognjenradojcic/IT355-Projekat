package com.ognjen.projekat.controller.dto.mapper;

import com.ognjen.projekat.controller.dto.request.RegisterRequest;
import com.ognjen.projekat.controller.dto.request.UpdateUserRequest;
import com.ognjen.projekat.controller.dto.response.LoginResponse;
import com.ognjen.projekat.controller.dto.response.UserResponse;
import com.ognjen.projekat.model.Tokens;
import com.ognjen.projekat.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserDtoMapper {
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);

    User toDomain(RegisterRequest request);

    LoginResponse toResponse(Tokens tokens);

    @Mapping(target = "id", source = "id")
    User toDomain(UpdateUserRequest request, Integer id);

}

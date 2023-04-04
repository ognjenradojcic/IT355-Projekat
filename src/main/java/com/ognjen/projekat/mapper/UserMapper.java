package com.ognjen.projekat.mapper;

import com.ognjen.projekat.model.User;
import com.ognjen.projekat.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = InvoiceMapper.class)
public interface UserMapper {

    UserEntity toEntity(User user);

    @Mapping(target = "invoices", qualifiedByName = "noUser")
    User toDomain(UserEntity userEntity);

    List<User> toDomainList(List<UserEntity> userEntityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "invoices", ignore = true)
    void update(@MappingTarget UserEntity userEntity, User user);
}

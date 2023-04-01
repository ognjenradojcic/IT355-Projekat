package com.ognjen.projekat.mapper;

import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.repository.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(uses = ProductMapper.class)
public interface CategoryMapper {

    CategoryEntity toEntity(Category category);

    @Mapping(target = "products", qualifiedByName = "noCategory")
    Category toDomain(CategoryEntity categoryEntity);

    List<Category> toDomainList(List<CategoryEntity> categoryEntityList);

    void update(@MappingTarget CategoryEntity categoryEntity, Category category);
}

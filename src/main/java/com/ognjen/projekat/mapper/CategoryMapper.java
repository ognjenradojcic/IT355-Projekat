package com.ognjen.projekat.mapper;

import com.ognjen.projekat.model.Category;
import com.ognjen.projekat.repository.entity.CategoryEntity;
import org.mapstruct.Mapper;


@Mapper
public interface CategoryMapper {
    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity categoryEntity);
}

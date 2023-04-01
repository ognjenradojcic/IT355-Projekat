package com.ognjen.projekat.controller.dto.mapper;


import com.ognjen.projekat.controller.dto.request.CategoryRequest;
import com.ognjen.projekat.controller.dto.request.CategoryUpdateRequest;
import com.ognjen.projekat.controller.dto.response.CategoryResponse;
import com.ognjen.projekat.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CategoryDtoMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categoryList);

    Category toDomain(CategoryRequest request);

    @Mapping(target = "id", source = "id")
    Category toDomain(CategoryUpdateRequest request, Integer id);

}

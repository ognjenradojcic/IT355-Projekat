package com.ognjen.projekat.controller.dto.mapper;


import com.ognjen.projekat.controller.dto.request.CategoryRequest;
import com.ognjen.projekat.controller.dto.response.CategoryResponse;
import com.ognjen.projekat.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryDtoMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categoryList);

    Category toDomain(CategoryRequest request);

}

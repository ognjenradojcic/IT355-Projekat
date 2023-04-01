package com.ognjen.projekat.controller.dto.mapper;

import com.ognjen.projekat.controller.dto.request.ProductRequest;
import com.ognjen.projekat.controller.dto.request.ProductUpdateRequest;
import com.ognjen.projekat.controller.dto.response.ProductResponse;
import com.ognjen.projekat.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductDtoMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> productList);

    @Mapping(target = "category.id", source = "categoryId")
    Product toDomain(ProductRequest request);

    @Mapping(target = "category.id", source = "request.categoryId")
    @Mapping(target = "id", source = "id")
    Product toDomain(ProductUpdateRequest request, Integer id);

}

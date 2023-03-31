package com.ognjen.projekat.controller.dto.mapper;

import com.ognjen.projekat.controller.dto.request.ProductRequest;
import com.ognjen.projekat.controller.dto.response.ProductResponse;
import com.ognjen.projekat.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductDtoMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> productList);

    Product toDomain(ProductRequest request);

}

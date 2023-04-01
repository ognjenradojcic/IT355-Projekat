package com.ognjen.projekat.mapper;


import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductEntity toEntity(Product product);

    @Mapping(target = "category.products", ignore = true)
    Product toDomain(ProductEntity productEntity);

    List<Product> toDomainList(List<ProductEntity> productEntityList);

    void update(@MappingTarget ProductEntity productEntity, Product product);

    @Named(value = "noCategory")
    @Mapping(target = "category", ignore = true)
    Product toDomainNoCategory(ProductEntity productEntity);
}

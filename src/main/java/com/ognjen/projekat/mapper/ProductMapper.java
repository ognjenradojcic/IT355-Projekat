package com.ognjen.projekat.mapper;


import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface ProductMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);

    List<Product> toDomainList(List<ProductEntity> productEntityList);

    void update(@MappingTarget ProductEntity productEntity, Product product);
}

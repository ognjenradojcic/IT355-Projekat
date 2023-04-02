package com.ognjen.projekat.repository;

import com.ognjen.projekat.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByIdIn(Set<Integer> productsId);
}

package com.ognjen.projekat.repository;

import com.ognjen.projekat.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByName(String name);

    void deleteById(Integer id);
}

package com.ognjen.projekat.repository;

import com.ognjen.projekat.repository.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

    void deleteById(Integer id);

    Optional<InvoiceEntity> findByIdAndUserUsername(Integer id, String username);

    List<InvoiceEntity> findAllByUserUsername(String username);
}

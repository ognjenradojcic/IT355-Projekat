package com.ognjen.projekat.repository;

import com.ognjen.projekat.repository.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

    void deleteById(Integer id);
}

package com.ognjen.projekat.mapper;


import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    InvoiceEntity toEntity(Invoice invoice);

    Invoice toDomain(InvoiceEntity invoiceEntity);

    List<Invoice> toDomainList(List<InvoiceEntity> invoiceEntityList);

    void update(@MappingTarget InvoiceEntity invoiceEntity, Invoice invoice);
}

package com.ognjen.projekat.mapper;


import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = ProductMapper.class)
public interface InvoiceMapper {

    InvoiceEntity toEntity(Invoice invoice);

    @Mapping(target = "user.invoices", ignore = true)
    @Mapping(target = "items.product", qualifiedByName = "noCategory")
    Invoice toDomain(InvoiceEntity invoiceEntity);


    List<Invoice> toDomainList(List<InvoiceEntity> invoiceEntityList);

    void update(@MappingTarget InvoiceEntity invoiceEntity, Invoice invoice);


    @Named(value = "noUser")
    @Mapping(target = "user", ignore = true)
    Invoice toDomainNoUser(InvoiceEntity invoiceEntity);
}

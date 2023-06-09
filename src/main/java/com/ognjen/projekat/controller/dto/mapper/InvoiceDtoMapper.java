package com.ognjen.projekat.controller.dto.mapper;


import com.ognjen.projekat.controller.dto.request.InvoiceRequest;
import com.ognjen.projekat.controller.dto.response.InvoiceResponse;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.model.InvoiceItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface InvoiceDtoMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderedDate", source = "orderedAt")
    InvoiceResponse toResponse(Invoice invoice);

    List<InvoiceResponse> toResponseList(List<Invoice> invoiceList);

    @Mapping(target = "user.id", source = "userId")
    Invoice toDomain(InvoiceRequest request);


    @Mapping(target = "product.id", source = "request.productId")
    InvoiceItem toDomain(InvoiceRequest.InvoiceItemRequest request);
}

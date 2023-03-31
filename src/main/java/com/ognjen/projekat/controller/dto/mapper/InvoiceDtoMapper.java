package com.ognjen.projekat.controller.dto.mapper;


import com.ognjen.projekat.controller.dto.request.InvoiceRequest;
import com.ognjen.projekat.controller.dto.response.InvoiceResponse;
import com.ognjen.projekat.model.Invoice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InvoiceDtoMapper {

    InvoiceResponse toResponse(Invoice invoice);

    List<Invoice> toResponseList(List<Invoice> invoiceList);

    Invoice toDomain(InvoiceRequest request);

}

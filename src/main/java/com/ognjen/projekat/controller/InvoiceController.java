package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.InvoiceDtoMapper;
import com.ognjen.projekat.controller.dto.request.InvoiceRequest;
import com.ognjen.projekat.controller.dto.response.InvoiceResponse;
import com.ognjen.projekat.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceDtoMapper mapper;


    @GetMapping
    public List<InvoiceResponse> getAll() {
        return mapper.toResponseList(invoiceService.getAll());
    }

    @GetMapping("/{id}")
    public InvoiceResponse getById(@PathVariable("id") Integer id) {
        return mapper.toResponse(invoiceService.getById(id));
    }

    @PostMapping
    public InvoiceResponse create(@RequestBody InvoiceRequest request) {
        return mapper.toResponse(invoiceService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Integer id) {
        invoiceService.delete(id);
    }

}

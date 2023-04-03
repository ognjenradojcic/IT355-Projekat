package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.annotation.UserAuthority;
import com.ognjen.projekat.controller.dto.mapper.InvoiceDtoMapper;
import com.ognjen.projekat.controller.dto.request.InvoiceRequest;
import com.ognjen.projekat.controller.dto.response.InvoiceResponse;
import com.ognjen.projekat.service.InvoiceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/invoices")
@SecurityRequirement(name = "bearer-key")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceDtoMapper mapper;


    @GetMapping
    @UserAuthority
    public List<InvoiceResponse> getAll() {
        return mapper.toResponseList(invoiceService.getAll());
    }

    @GetMapping("/{id}")
    @UserAuthority
    public InvoiceResponse getById(@PathVariable("id") Integer id) {
        return mapper.toResponse(invoiceService.getById(id));
    }

    @PostMapping
    @UserAuthority
    public InvoiceResponse create(@RequestBody @Valid InvoiceRequest request) {
        return mapper.toResponse(invoiceService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @UserAuthority
    private void delete(@PathVariable("id") Integer id) {
        invoiceService.delete(id);
    }

}

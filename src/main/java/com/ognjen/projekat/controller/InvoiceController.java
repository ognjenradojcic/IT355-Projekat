package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.annotation.AdminAuthority;
import com.ognjen.projekat.controller.annotation.UserAuthority;
import com.ognjen.projekat.controller.dto.mapper.InvoiceDtoMapper;
import com.ognjen.projekat.controller.dto.request.InvoiceRequest;
import com.ognjen.projekat.controller.dto.response.InvoiceResponse;
import com.ognjen.projekat.service.InvoiceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @AdminAuthority
    public List<InvoiceResponse> getAll() {
        return mapper.toResponseList(invoiceService.getAll());
    }

    @GetMapping("/users")
    @UserAuthority
    public List<InvoiceResponse> getAllForUser(@RequestHeader(name = "Authorization") String token) {
        return mapper.toResponseList(invoiceService.getAllForUser(token));
    }

    @GetMapping("/{id}")
    @UserAuthority
    public InvoiceResponse getById(@PathVariable("id") Integer id, @RequestHeader(name = "Authorization") String token) {
        return mapper.toResponse(invoiceService.getByIdAndUsername(id, token));
    }

    @PostMapping
    @UserAuthority
    public InvoiceResponse create(@RequestBody @Valid InvoiceRequest request) {
        return mapper.toResponse(invoiceService.create(mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @UserAuthority
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        invoiceService.delete(id);
    }

}

package com.ognjen.projekat.service;

import com.ognjen.projekat.mapper.InvoiceMapper;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.InvoiceRepository;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.ognjen.projekat.EntityBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @InjectMocks
    InvoiceService invoiceService;

    @Spy
    InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);


    @Test
    void getAll() {
        List<InvoiceEntity> invoiceEntities = Collections.singletonList(invoiceEntity());

        when(invoiceRepository.findAll()).thenReturn(invoiceEntities);

        List<Invoice> invoices = invoiceService.getAll();

        assertEquals(INVOICE_ID, invoices.get(0).getId());

    }

    @Test
    void getById() {

        when(invoiceRepository.findById(1)).thenReturn(
                Optional.of(invoiceEntity()));

        Invoice invoice = invoiceService.getById(1, USER_ID);

        assertEquals(INVOICE_ID, invoice.getId());

    }

    @Test
    void create() {
        List<Product> products = new ArrayList<>();
        products.add(product());

        when(userService.getById(invoice().getUser().getId())).thenReturn(invoice().getUser());
        when(invoiceRepository.save(any(InvoiceEntity.class))).thenReturn(invoiceEntity());
        when(productService.getAllByIdsIn(any(Set.class))).thenReturn(products);

        Invoice invoice = invoiceService.create(invoice());

        assertEquals(INVOICE_ID, invoice.getId());

    }

    @Test
    void delete() {
        when(invoiceRepository.findById(INVOICE_ID)).thenReturn(Optional.ofNullable(invoiceEntity()));
        invoiceService.delete(INVOICE_ID, USER_ID);
        verify(invoiceRepository).deleteById(INVOICE_ID);
    }
}
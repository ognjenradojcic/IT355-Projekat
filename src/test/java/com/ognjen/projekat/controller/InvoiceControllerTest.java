package com.ognjen.projekat.controller;

import com.ognjen.projekat.controller.dto.mapper.InvoiceDtoMapper;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.service.InvoiceService;
import com.ognjen.projekat.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static com.ognjen.projekat.EntityBuilder.invoice;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(InvoiceController.class)
@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceService invoiceService;

    @MockBean
    TokenService service;

    @MockBean
    InvoiceDtoMapper mapper;

    @MockBean
    Authentication auth;

    @Test
    void getAll() throws Exception {
        when(invoiceService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/invoices"))
                .andExpect(status().isOk());
    }

//    @Test
//    void getById() throws Exception {
//        when(invoiceService.getById(1, USER_ID)).thenReturn(invoice());
//        when(auth.getPrincipal()).thenReturn(user());
//
//        mockMvc.perform(get("/v1/invoices/{id}", 1)
//                        .with(authentication(auth)))
//                .andExpect(status().isOk());
//    }

    @Test
    void create() throws Exception {
        when(invoiceService.create(any(Invoice.class))).thenReturn(invoice());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/invoices")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"address\":\"Bulevar\",\"userId\":1,\"items\":[{\"productId\":1, \"quantity\":1}]}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

//    @Test
//    void delete() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/invoices/{id}", 1)
//                        .with(SecurityMockMvcRequestPostProcessors.user(USERNAME).roles(ROLE.name())))
//                .andExpect(status().isNoContent());
//    }
}
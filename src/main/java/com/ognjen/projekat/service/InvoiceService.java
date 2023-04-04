package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.InvoiceMapper;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.repository.InvoiceRepository;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceMapper mapper;
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    public List<Invoice> getAll() {
        return mapper.toDomainList(invoiceRepository.findAll());
    }

    public Invoice getById(Integer orderId) {
        return mapper.toDomain(getInvoiceEntityById(orderId));
    }

    @Transactional
    public Invoice create(Invoice invoice) {
        invoice.setUser(userService.getById(invoice.getUser().getId()));

        var entity = mapper.toEntity(invoice);

        return mapper.toDomain(invoiceRepository.save(entity));
    }

    @Transactional
    public void delete(Integer orderId) {
        invoiceNotExistsByIdCheck(orderId);

        invoiceRepository.deleteById(orderId);
    }

    private InvoiceEntity getInvoiceEntityById(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() ->
                new NotFoundException("Invoice not found with id: " + invoiceId));
    }

    private void invoiceNotExistsByIdCheck(Integer id) {
        if (!invoiceRepository.existsById(id)) {
            throw new NotFoundException("Invoice not found with id: " + id);
        }
    }

}

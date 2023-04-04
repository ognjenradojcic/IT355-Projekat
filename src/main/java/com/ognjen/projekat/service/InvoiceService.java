package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.InvoiceMapper;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.model.InvoiceItem;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.InvoiceRepository;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceMapper mapper;
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final ProductService productService;
    private final TokenService tokenService;

    public List<Invoice> getAll() {
        return mapper.toDomainList(invoiceRepository.findAll());
    }

    public List<Invoice> getAllForUser(String token) {
        return mapper.toDomainList(getAllByUsername(token));
    }

    public Invoice getById(Integer invoiceId) {
        return mapper.toDomain(getInvoiceEntityById(invoiceId));
    }

    public Invoice getByIdAndUsername(Integer invoiceId, String token) {
        return mapper.toDomain(getInvoiceEntityByIdAndUsername(invoiceId, token));
    }

    @Transactional
    public Invoice create(Invoice invoice) {
        invoice.setUser(userService.getById(invoice.getUser().getId()));
        invoice.setItems(addProductToInvoiceItems(invoice.getItems()));

        var entity = mapper.toEntity(invoice);

        return mapper.toDomain(invoiceRepository.saveAndFlush(entity));

    }

    @Transactional
    public void delete(Integer invoiceId) {
        invoiceNotExistsByIdCheck(invoiceId);

        invoiceRepository.deleteById(invoiceId);
    }

    private InvoiceEntity getInvoiceEntityById(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() ->
                new NotFoundException("Invoice not found with id: " + invoiceId));
    }

    private InvoiceEntity getInvoiceEntityByIdAndUsername(Integer invoiceId, String token) {
        String username = tokenService.extractUsername(token.substring(7));
        return invoiceRepository.findByIdAndUserUsername(invoiceId, username).orElseThrow(() ->
                new NotFoundException("Invoice not found with id: " + invoiceId + ", for username: " + username));
    }

    private List<InvoiceEntity> getAllByUsername(String token) {
        String username = tokenService.extractUsername(token.substring(7));
        return invoiceRepository.findAllByUserUsername(username);
    }

    private void invoiceNotExistsByIdCheck(Integer id) {
        if (!invoiceRepository.existsById(id)) {
            throw new NotFoundException("Invoice not found with id: " + id);
        }
    }

    // TODO: 5.4.2023. Proveriti da li postoji drugi nacin
    private List<InvoiceItem> addProductToInvoiceItems(List<InvoiceItem> invoiceItems) {
        List<Product> products = productService.getAll();
        Map<Integer, Product> productMap = new HashMap<>();
        for (Product product : products) {
            productMap.put(product.getId(), product);
        }

        for (InvoiceItem invoiceItem : invoiceItems) {
            Integer productId = invoiceItem.getProduct().getId();
            Product product = productMap.get(productId);
            if (product == null) {
                throw new NotFoundException("Product not found for id: " + productId);
            } else {
                invoiceItem.setProduct(product);
            }
        }
        return invoiceItems;
    }


}

package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.AuthorizationFailedException;
import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.InvoiceMapper;
import com.ognjen.projekat.model.Invoice;
import com.ognjen.projekat.model.InvoiceItem;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.repository.InvoiceRepository;
import com.ognjen.projekat.repository.entity.InvoiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceMapper mapper;
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final ProductService productService;

    public List<Invoice> getAll() {
        return mapper.toDomainList(invoiceRepository.findAll());
    }

    public List<Invoice> getAllForUser(User user) {
        return mapper.toDomainList(getAllByUsername(user.getUsername()));
    }

    public Invoice getById(Integer invoiceId, Integer loggedUserId) {

        InvoiceEntity invoiceEntity = getInvoiceEntityById(invoiceId);

        authorizeById(invoiceEntity.getUser().getId(), loggedUserId);

        return mapper.toDomain(invoiceEntity);
    }

    @Transactional
    public Invoice create(Invoice invoice) {

        invoice.setUser(userService.getById(invoice.getUser().getId()));
        invoice.setItems(addProductToInvoiceItems(invoice.getItems()));

        var entity = mapper.toEntity(invoice);

        return mapper.toDomain(invoiceRepository.save(entity));

    }

    @Transactional
    public void delete(Integer invoiceId, Integer loggedUserId) {

        InvoiceEntity invoiceEntity = getInvoiceEntityById(invoiceId);

        authorizeById(invoiceEntity.getUser().getId(), loggedUserId);

        invoiceRepository.deleteById(invoiceId);
    }


    private InvoiceEntity getInvoiceEntityById(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() ->
                new NotFoundException("Invoice not found with id: " + invoiceId));
    }

    private List<InvoiceEntity> getAllByUsername(String username) {
        return invoiceRepository.findAllByUserUsername(username);
    }

    private static void authorizeById(Integer userId, Integer loggedUserId) {
        if (!loggedUserId.equals(userId)) {
            throw new AuthorizationFailedException("Authorization has failed");
        }
    }

    // TODO: 5.4.2023. Proveriti da li postoji drugi nacin
    private List<InvoiceItem> addProductToInvoiceItems(List<InvoiceItem> invoiceItems) {

        var productIds = invoiceItems.stream()
                .map(InvoiceItem::getProduct)
                .map(Product::getId)
                .collect(Collectors.toSet());

        var products = productService.getAllByIdsIn(productIds);

        Map<Integer, Product> productPerId = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        for (InvoiceItem invoiceItem : invoiceItems) {

            var productId = invoiceItem.getProduct().getId();
            var product = productPerId.get(productId);

            if (product == null) {
                throw new NotFoundException("Product not found for id: " + productId);
            } else {
                invoiceItem.setProduct(product);
            }
        }

        return invoiceItems;
    }
}

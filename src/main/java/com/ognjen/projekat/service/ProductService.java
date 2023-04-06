package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.ProductMapper;
import com.ognjen.projekat.model.Product;
import com.ognjen.projekat.repository.ProductRepository;
import com.ognjen.projekat.repository.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> getAll() {
        return mapper.toDomainList(productRepository.findAll());
    }

    public Product getById(Integer productId) {
        return mapper.toDomain(getProductEntityById(productId));
    }

    @Transactional
    public Product create(Product product) {

        product.setCategory(categoryService.getById(product.getCategory().getId()));

        var entity = mapper.toEntity(product);

        return mapper.toDomain(productRepository.save(entity));
    }

    @Transactional
    public void delete(Integer productId) {

        validateProductExists(productId);

        productRepository.deleteById(productId);
    }

    @Transactional
    public void update(Product updatedProduct) {

        updatedProduct.setCategory(categoryService.getById(updatedProduct.getCategory().getId()));

        var existingProductEntity = getProductEntityById(updatedProduct.getId());

        mapper.update(existingProductEntity, updatedProduct);
    }

    private ProductEntity getProductEntityById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found with id: " + productId));
    }

    private void validateProductExists(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found with id: " + id);
        }
    }

    public List<Product> getAllByIdsIn(Set<Integer> productIds) {
        return mapper.toDomainList(productRepository.findByIdIn(productIds));
    }
}

package com.ognjen.projekat.service;


import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.CustomerMapper;
import com.ognjen.projekat.model.Customer;
import com.ognjen.projekat.repository.CustomerRepository;
import com.ognjen.projekat.repository.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper mapper;

    public List<Customer> getAll() {
        return mapper.toDomainList(customerRepository.findAll());
    }

    public Customer getById(Integer customerId) {
        return mapper.toDomain(getCustomerEntityById(customerId));
    }

    @Transactional
    public Customer create(Customer customer) {

        var entity = mapper.toEntity(customer);

        return mapper.toDomain(customerRepository.save(entity));
    }

    @Transactional
    public void delete(Integer customerId) {
        customerRepository.delete(getCustomerEntityById(customerId));
    }

    @Transactional
    public void update(Customer updatedCustomer) {

        var existingCustomerEntity = getCustomerEntityById(updatedCustomer.getId());

        mapper.update(existingCustomerEntity, updatedCustomer);
    }

    private CustomerEntity getCustomerEntityById(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() ->
                new NotFoundException("Customer not found with id: " + customerId));
    }

}

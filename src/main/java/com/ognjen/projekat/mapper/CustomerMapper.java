package com.ognjen.projekat.mapper;

import com.ognjen.projekat.model.Customer;
import com.ognjen.projekat.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity customerEntity);

    List<Customer> toDomainList(List<CustomerEntity> customerEntityList);

    void update(@MappingTarget CustomerEntity customerEntity, Customer customer);
}

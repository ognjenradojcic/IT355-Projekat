package com.ognjen.projekat.mapper;


import com.ognjen.projekat.model.Order;
import com.ognjen.projekat.repository.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface OrderMapper {

    OrderEntity toEntity(Order order);

    Order toDomain(OrderEntity orderEntity);

    List<Order> toDomainList(List<OrderEntity> orderEntityList);

    void update(@MappingTarget OrderEntity orderEntity, Order order);
}

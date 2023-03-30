package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.OrderMapper;
import com.ognjen.projekat.model.Order;
import com.ognjen.projekat.repository.OrderRepository;
import com.ognjen.projekat.repository.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private OrderMapper mapper;
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return mapper.toDomainList(orderRepository.findAll());
    }

    public Order getById(Integer orderId) {
        return mapper.toDomain(getOrderEntityById(orderId));
    }

    @Transactional
    public Order create(Order order) {

        var entity = mapper.toEntity(order);

        return mapper.toDomain(orderRepository.save(entity));
    }

    @Transactional
    public void delete(Integer orderId) {
        orderRepository.delete(getOrderEntityById(orderId));
    }

    @Transactional
    public void update(Order updatedOrder) {

        var existingOrderEntity = getOrderEntityById(updatedOrder.getId());

        mapper.update(existingOrderEntity, updatedOrder);
    }

    private OrderEntity getOrderEntityById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new NotFoundException("Order not found with id: " + orderId));
    }


}

package com.ognjen.projekat.controller.dto.mapper;


import com.ognjen.projekat.controller.dto.request.OrderRequest;
import com.ognjen.projekat.controller.dto.response.OrderResponse;
import com.ognjen.projekat.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderDtoMapper {

    OrderResponse toResponse(Order order);

    List<Order> toResponseList(List<Order> orderList);

    Order toDomain(OrderRequest request);

}

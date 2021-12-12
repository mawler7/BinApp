package com.management.warehouse.model.order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static OrderDto convertToOrderDto(Order order) {
        if (order == null) return null;
        return OrderDto.builder()
                .id(order.getId())
                .truck(order.getTruck())
                .container(order.getContainer())
                .amount(order.getAmount())
                .type(order.getType())
                .user(order.getUser())
                .date(order.getDate())
                .delivered(order.isDelivered())
                .dateDelivered(order.getDateDelivered())
                .build();
    }

    public static Order convertToOrder(OrderDto orderDto) {
        if (orderDto == null) return null;
        return Order.builder()
                .id(orderDto.getId())
                .truck(orderDto.getTruck())
                .container(orderDto.getContainer())
                .amount(orderDto.getAmount())
                .type(orderDto.getType())
                .user(orderDto.getUser())
                .date(orderDto.getDate())
                .delivered(orderDto.isDelivered())
                .dateDelivered(orderDto.getDateDelivered())
                .build();
    }

    public static List<Order> convertOrderDtoToList(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(OrderConverter::convertToOrder)
                .collect(Collectors.toList());
    }

    public static List<OrderDto> convertOrderListToDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(OrderConverter::convertToOrderDto)
                .collect(Collectors.toList());
    }
}

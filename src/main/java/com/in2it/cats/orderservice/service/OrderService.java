package com.in2it.cats.orderservice.service;

import com.in2it.cats.orderservice.dto.OrderRequestDTO;
import com.in2it.cats.orderservice.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);

    OrderResponseDTO getOrderById(String id);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrder(
            String id,
            OrderRequestDTO request);

    void deleteOrder(String id);
}
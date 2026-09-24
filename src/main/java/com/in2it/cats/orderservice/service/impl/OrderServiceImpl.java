package com.in2it.cats.orderservice.service.impl;

import com.in2it.cats.orderservice.client.ProductClient;
import com.in2it.cats.orderservice.dto.OrderRequestDTO;
import com.in2it.cats.orderservice.dto.OrderResponseDTO;
import com.in2it.cats.orderservice.dto.ProductDTO;
import com.in2it.cats.orderservice.dto.ProductResponseDTO;
import com.in2it.cats.orderservice.entity.Order;
import com.in2it.cats.orderservice.exception.OrderNotFoundException;
import com.in2it.cats.orderservice.repository.OrderRepository;
import com.in2it.cats.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        ProductResponseDTO productResponse =
                productClient.getProductById(request.getProductId());

        ProductDTO product = productResponse.getData();

        BigDecimal price = product.getPrice();

        BigDecimal totalAmount =
                price.multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setPrice(price);
        order.setTotalAmount(totalAmount);
        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);

        return OrderResponseDTO.builder()
                .id(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .productId(savedOrder.getProductId())
                .quantity(savedOrder.getQuantity())
                .price(savedOrder.getPrice())
                .totalAmount(savedOrder.getTotalAmount())
                .status(savedOrder.getStatus())
                .build();
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(id));

        return OrderResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .build();
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> OrderResponseDTO.builder()
                        .id(order.getId())
                        .userId(order.getUserId())
                        .productId(order.getProductId())
                        .quantity(order.getQuantity())
                        .price(order.getPrice())
                        .totalAmount(order.getTotalAmount())
                        .status(order.getStatus())
                        .build())
                .toList();
    }

    @Override
    public OrderResponseDTO updateOrder(
            String id,
            OrderRequestDTO request) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(id));

        BigDecimal price = order.getPrice();

        BigDecimal totalAmount =
                price.multiply(BigDecimal.valueOf(request.getQuantity()));

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(totalAmount);

        Order updatedOrder = orderRepository.save(order);

        return OrderResponseDTO.builder()
                .id(updatedOrder.getId())
                .userId(updatedOrder.getUserId())
                .productId(updatedOrder.getProductId())
                .quantity(updatedOrder.getQuantity())
                .price(updatedOrder.getPrice())
                .totalAmount(updatedOrder.getTotalAmount())
                .status(updatedOrder.getStatus())
                .build();
    }

    @Override
    public void deleteOrder(String id) {

        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }

        orderRepository.deleteById(id);
    }
}
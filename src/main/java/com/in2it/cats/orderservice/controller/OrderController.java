package com.in2it.cats.orderservice.controller;

import com.in2it.cats.orderservice.dto.OrderRequestDTO;
import com.in2it.cats.orderservice.dto.OrderResponseDTO;
import com.in2it.cats.orderservice.dto.ResponseDTO;
import com.in2it.cats.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "${order.create}")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createOrder(
            @Valid @RequestBody OrderRequestDTO request) {

        OrderResponseDTO data =
                orderService.createOrder(request);

        ResponseDTO response =
                new ResponseDTO(true, data, null);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "${order.getById}")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(
            @PathVariable String id) {

        OrderResponseDTO data =
                orderService.getOrderById(id);

        ResponseDTO response =
                new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${order.getAll}")
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllOrders() {

        List<OrderResponseDTO> data =
                orderService.getAllOrders();

        ResponseDTO response =
                new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${order.update}")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateOrder(
            @PathVariable String id,
            @Valid @RequestBody OrderRequestDTO request) {

        OrderResponseDTO data =
                orderService.updateOrder(id, request);

        ResponseDTO response =
                new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${order.delete}")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(
            @PathVariable String id) {

        orderService.deleteOrder(id);

        ResponseDTO response =
                new ResponseDTO(true, null, null);

        return ResponseEntity.ok(response);
    }
}
package com.in2it.cats.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private String id;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
}
package com.in2it.cats.orderservice.client;

import com.in2it.cats.orderservice.dto.PaymentRequestDTO;
import com.in2it.cats.orderservice.dto.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8085")
public interface PaymentClient {

    @PostMapping("/api/payments/create")
    PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO request);
}
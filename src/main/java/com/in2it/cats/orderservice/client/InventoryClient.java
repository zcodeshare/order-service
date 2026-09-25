package com.in2it.cats.orderservice.client;

import com.in2it.cats.orderservice.dto.InventoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8084")
public interface InventoryClient {

    @GetMapping("/api/inventory/product/{productId}")
    InventoryResponseDTO getInventoryByProductId(@PathVariable String productId);
}
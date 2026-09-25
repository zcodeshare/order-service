package com.in2it.cats.orderservice.client;

import com.in2it.cats.orderservice.dto.NotificationRequestDTO;
import com.in2it.cats.orderservice.dto.NotificationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8086")
public interface NotificationClient {

    @PostMapping("/api/notifications/create")
    NotificationResponseDTO createNotification(@RequestBody NotificationRequestDTO request);
}
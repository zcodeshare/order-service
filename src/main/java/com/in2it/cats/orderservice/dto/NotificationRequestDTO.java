package com.in2it.cats.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {

    private String userId;
    private String orderId;
    private String type;
    private String message;
    private String channel;
}
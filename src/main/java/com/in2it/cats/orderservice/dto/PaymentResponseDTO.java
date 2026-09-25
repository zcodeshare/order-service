package com.in2it.cats.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    @JsonProperty("is_success")
    private boolean isSuccess;

    private PaymentDTO data;

    @JsonProperty("error_info")
    private CustomErrorResponseDTO errorInfo;
}
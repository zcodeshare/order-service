package com.in2it.cats.orderservice.exception;

import com.in2it.cats.orderservice.constant.OrderConstants;
import com.in2it.cats.orderservice.dto.CustomErrorResponseDTO;
import com.in2it.cats.orderservice.dto.ResponseDTO;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleOrderNotFound(
            OrderNotFoundException exception) {

        CustomErrorResponseDTO errorInfo =
                new CustomErrorResponseDTO(
                        "ORDER_NOT_FOUND",
                        exception.getMessage(),
                        "No order exists with the given id: " + exception.getMessage()
                );

        ResponseDTO response =
                new ResponseDTO(false, null, errorInfo);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        CustomErrorResponseDTO errorInfo =
                new CustomErrorResponseDTO(
                        "VALIDATION_ERROR",
                        OrderConstants.VALIDATION_ERROR,
                        errors.toString()
                );

        ResponseDTO response =
                new ResponseDTO(false, null, errorInfo);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(
            Exception exception) {

        CustomErrorResponseDTO errorInfo =
                new CustomErrorResponseDTO(
                        "INTERNAL_SERVER_ERROR",
                        OrderConstants.INTERNAL_SERVER_ERROR,
                        exception.getMessage()
                );

        ResponseDTO response =
                new ResponseDTO(false, null, errorInfo);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(
            FeignException.NotFound exception) {

        CustomErrorResponseDTO errorInfo =
                new CustomErrorResponseDTO(
                        "PRODUCT_NOT_FOUND",
                        "Product not found",
                        "The requested product does not exist"
                );

        ResponseDTO response =
                new ResponseDTO(false, null, errorInfo);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
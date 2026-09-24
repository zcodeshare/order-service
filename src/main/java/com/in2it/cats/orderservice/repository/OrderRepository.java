package com.in2it.cats.orderservice.repository;

import com.in2it.cats.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
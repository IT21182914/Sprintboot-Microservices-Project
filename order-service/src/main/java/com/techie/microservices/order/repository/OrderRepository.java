package com.techie.microservices.order.repository;

import com.techie.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA means Java Persistence API which use JPA to interact with the database. Save, update, delete, and find records in the database.
public interface OrderRepository extends JpaRepository<Order, Long> {
}
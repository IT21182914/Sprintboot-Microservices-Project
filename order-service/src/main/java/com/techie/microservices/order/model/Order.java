package com.techie.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity // This annotation is used to specify that the class is an entity and is mapped to a database table
@Table(name = "t_orders") // This annotation is used to specify the name of the table in the database
@Getter // Lombok annotation to generate automatic getters for the fields
@Setter  // Lombok annotation to generate automatic setters for the fields
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as parameters
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor - default constructor
public class Order {

    @Id // This annotation is used to specify the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generate the primary key value - auto-increment
    private Long id;
    private String orderNumber;
    private String skuCode; // Stock Keeping Unit code
    private BigDecimal price;
    private Integer quantity;
}
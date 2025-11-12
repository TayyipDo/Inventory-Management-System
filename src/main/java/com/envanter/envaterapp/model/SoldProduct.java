package com.envanter.envaterapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sold_products")
public class SoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private double price;

    private String customerName;

    private String customerPhone;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

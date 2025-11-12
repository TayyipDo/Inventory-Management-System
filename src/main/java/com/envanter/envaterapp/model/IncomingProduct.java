package com.envanter.envaterapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "incoming_products")
public class IncomingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private double price;

    private boolean purchased; // ✔ Bu alan "alındı mı?" bilgisini tutar

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

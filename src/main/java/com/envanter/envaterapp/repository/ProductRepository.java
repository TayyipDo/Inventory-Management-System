package com.envanter.envaterapp.repository;

import com.envanter.envaterapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

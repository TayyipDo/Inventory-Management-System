package com.envanter.envaterapp.repository;

import com.envanter.envaterapp.model.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {
}

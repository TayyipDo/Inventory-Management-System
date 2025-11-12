package com.envanter.envaterapp.repository;

import com.envanter.envaterapp.model.IncomingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomingProductRepository extends JpaRepository<IncomingProduct, Long> {
}

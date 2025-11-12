package com.envanter.envaterapp.service;

import com.envanter.envaterapp.model.SoldProduct;

import java.util.List;

public interface SoldProductService {
    SoldProduct saveSoldProduct(SoldProduct product);
    List<SoldProduct> getAllSoldProducts();
    SoldProduct getSoldProductById(Long id);
    void deleteSoldProduct(Long id);
}

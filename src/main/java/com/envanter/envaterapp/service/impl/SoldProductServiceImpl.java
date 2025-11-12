package com.envanter.envaterapp.service.impl;

import com.envanter.envaterapp.model.SoldProduct;
import com.envanter.envaterapp.repository.SoldProductRepository;
import com.envanter.envaterapp.service.SoldProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldProductServiceImpl implements SoldProductService {

    @Autowired
    private SoldProductRepository soldProductRepository;

    @Override
    public SoldProduct saveSoldProduct(SoldProduct product) {
        return soldProductRepository.save(product);
    }

    @Override
    public List<SoldProduct> getAllSoldProducts() {
        return soldProductRepository.findAll();
    }

    @Override
    public SoldProduct getSoldProductById(Long id) {
        return soldProductRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSoldProduct(Long id) {
        soldProductRepository.deleteById(id);
    }
}

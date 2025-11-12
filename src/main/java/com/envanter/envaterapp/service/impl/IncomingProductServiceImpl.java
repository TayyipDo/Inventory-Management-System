package com.envanter.envaterapp.service.impl;

import com.envanter.envaterapp.model.IncomingProduct;
import com.envanter.envaterapp.repository.IncomingProductRepository;
import com.envanter.envaterapp.service.IncomingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingProductServiceImpl implements IncomingProductService {

    @Autowired
    private IncomingProductRepository incomingProductRepository;

    @Override
    public IncomingProduct saveIncomingProduct(IncomingProduct product) {
        return incomingProductRepository.save(product);
    }

    @Override
    public List<IncomingProduct> getAllIncomingProducts() {
        return incomingProductRepository.findAll();
    }

    @Override
    public IncomingProduct getIncomingProductById(Long id) {
        return incomingProductRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIncomingProduct(Long id) {
        incomingProductRepository.deleteById(id);
    }

    @Override
    public void markAsPurchased(Long id) {
        IncomingProduct product = getIncomingProductById(id);
        if (product != null) {
            product.setPurchased(true);
            incomingProductRepository.save(product);
        }
    }
}

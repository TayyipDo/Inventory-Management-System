package com.envanter.envaterapp.service;

import com.envanter.envaterapp.model.IncomingProduct;

import java.util.List;

public interface IncomingProductService {
    IncomingProduct saveIncomingProduct(IncomingProduct product);
    List<IncomingProduct> getAllIncomingProducts();
    IncomingProduct getIncomingProductById(Long id);
    void deleteIncomingProduct(Long id);
    void markAsPurchased(Long id);
}

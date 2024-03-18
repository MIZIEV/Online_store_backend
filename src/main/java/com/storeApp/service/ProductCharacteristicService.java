package com.storeApp.service;

import com.storeApp.models.ProductCharacteristic;

import java.util.List;

public interface ProductCharacteristicService {

    List<ProductCharacteristic> getAllCharacteristics();
    ProductCharacteristic addNewCharacteristic(ProductCharacteristic characteristic,Long productId);
    ProductCharacteristic updateCharacteristic(ProductCharacteristic characteristic,Long characteristicId);
    void deleteCharacteristic(Long characteristicId);
}

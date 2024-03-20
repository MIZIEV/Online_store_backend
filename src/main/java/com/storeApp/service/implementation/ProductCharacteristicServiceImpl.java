package com.storeApp.service.implementation;

import com.storeApp.models.Product;
import com.storeApp.models.ProductCharacteristic;
import com.storeApp.repository.ProductCharacteristicRepository;
import com.storeApp.repository.ProductRepository;
import com.storeApp.service.ProductCharacteristicService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductCharacteristicServiceImpl implements ProductCharacteristicService {

    private final ProductCharacteristicRepository productCharacteristicRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductCharacteristicServiceImpl(ProductCharacteristicRepository productCharacteristicRepository, ProductRepository productRepository) {
        this.productCharacteristicRepository = productCharacteristicRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductCharacteristic> getAllCharacteristics() {
        return productCharacteristicRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public ProductCharacteristic addNewCharacteristic(ProductCharacteristic characteristic, Long productId) {
        Optional<Product> product = productRepository.findProductById(productId);

        if (product.isPresent()) {
            characteristic.setProduct(product.get());
            productCharacteristicRepository.save(characteristic);
            return characteristic;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public ProductCharacteristic updateCharacteristic(ProductCharacteristic characteristic, Long characteristicId) {

        ProductCharacteristic characteristicForUpdate = null;

        if (productCharacteristicRepository.findProductCharacteristicById(characteristicId).isPresent()) {
            characteristicForUpdate = productCharacteristicRepository.findProductCharacteristicById(characteristicId).get();

            characteristicForUpdate.setId(characteristicId);
            characteristicForUpdate.setCharacteristicName(characteristic.getCharacteristicName());
            characteristicForUpdate.setCharacteristicValue(characteristic.getCharacteristicValue());

            productCharacteristicRepository.save(characteristicForUpdate);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                    "Characteristic with id-" + characteristicId + " not found!");
        }

        return characteristicForUpdate;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCharacteristic(Long characteristicId) {
        Optional<ProductCharacteristic> characteristic =
                productCharacteristicRepository.findProductCharacteristicById(characteristicId);

        if (characteristic.isPresent()) {
            productCharacteristicRepository.delete(characteristic.get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                    "Characteristic with id-" + characteristicId + " not found!");
        }
    }
}
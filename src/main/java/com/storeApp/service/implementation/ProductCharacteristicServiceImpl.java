package com.storeApp.service.implementation;

import com.storeApp.models.ProductCharacteristic;
import com.storeApp.repository.ProductCharacteristicRepository;
import com.storeApp.service.ProductCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductCharacteristicServiceImpl implements ProductCharacteristicService {

    private final ProductCharacteristicRepository productCharacteristicRepository;

    @Autowired
    public ProductCharacteristicServiceImpl(ProductCharacteristicRepository productCharacteristicRepository) {
        this.productCharacteristicRepository = productCharacteristicRepository;
    }

    @Override
    public List<ProductCharacteristic> getAllCharacteristics() {
        return productCharacteristicRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public ProductCharacteristic addNewCharacteristic(ProductCharacteristic characteristic) {
        ProductCharacteristic newCharacteristic = productCharacteristicRepository.save(characteristic);
        return newCharacteristic;
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
            throw null;  //todo create exception
        }

        return characteristicForUpdate;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCharacteristic(Long characteristicId) {
        ProductCharacteristic characteristic =
                productCharacteristicRepository.findProductCharacteristicById(characteristicId).get();

        productCharacteristicRepository.delete(characteristic);

    }
}
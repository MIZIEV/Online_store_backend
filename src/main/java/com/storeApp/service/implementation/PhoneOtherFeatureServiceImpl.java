package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.OtherFeatures;
import com.storeApp.repository.OtherFeatureRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.PhoneOtherFeaturesService;

import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PhoneOtherFeatureServiceImpl implements PhoneOtherFeaturesService {

    private final OtherFeatureRepository otherFeatureRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneOtherFeatureServiceImpl(OtherFeatureRepository otherFeatureRepository, PhoneRepository phoneRepository) {
        this.otherFeatureRepository = otherFeatureRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<OtherFeatures> getAllCharacteristics() {
        return otherFeatureRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public OtherFeatures addNewCharacteristic(OtherFeatures characteristic, Long productId) {
        Optional<Phone> product = phoneRepository.findPhoneById(productId);

        if (product.isPresent()) {
            characteristic.setPhone(product.get());
            otherFeatureRepository.save(characteristic);
            return characteristic;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public OtherFeatures updateCharacteristic(OtherFeatures characteristic, Long characteristicId) {

        OtherFeatures characteristicForUpdate = null;

        if (otherFeatureRepository.findProductCharacteristicById(characteristicId).isPresent()) {
            characteristicForUpdate = otherFeatureRepository.findProductCharacteristicById(characteristicId).get();

            characteristicForUpdate.setId(characteristicId);
            characteristicForUpdate.setFeature(characteristic.getFeature());
            characteristicForUpdate.setFeature(characteristic.getFeature());

            otherFeatureRepository.save(characteristicForUpdate);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                    "Characteristic with id-" + characteristicId + " not found!");
        }

        return characteristicForUpdate;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCharacteristic(Long characteristicId) {
        Optional<OtherFeatures> characteristic =
                otherFeatureRepository.findProductCharacteristicById(characteristicId);

        if (characteristic.isPresent()) {
            otherFeatureRepository.delete(characteristic.get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                    "Characteristic with id-" + characteristicId + " not found!");
        }
    }
}
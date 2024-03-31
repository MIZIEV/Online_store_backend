package com.storeApp.service;

import com.storeApp.models.OtherFeatures;

import java.util.List;

public interface PhoneOtherFeaturesService {

    List<OtherFeatures> getAllCharacteristics();
    OtherFeatures addNewCharacteristic(OtherFeatures characteristic, Long productId);
    OtherFeatures updateCharacteristic(OtherFeatures characteristic, Long characteristicId);
    void deleteCharacteristic(Long characteristicId);
}

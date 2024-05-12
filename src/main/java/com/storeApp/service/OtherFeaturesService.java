package com.storeApp.service;

import com.storeApp.models.OtherFeatures;

import java.util.List;

public interface OtherFeaturesService {

    OtherFeatures addNewFeature(OtherFeatures otherFeatures, Long phoneId);

    List<OtherFeatures> getAllFeatures(Long phoneId);

    OtherFeatures getFeatureById(int id);

    OtherFeatures updateFeature(OtherFeatures otherFeatures, Long id);

    void deleteFeature(Long id);
}
package com.storeApp.service.implementation;

import com.storeApp.models.OtherFeatures;
import com.storeApp.models.PhoneRom;
import com.storeApp.repository.OtherFeatureRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.OtherFeaturesService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OtherFeaturesImpl implements OtherFeaturesService {

    private final OtherFeatureRepository otherFeatureRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public OtherFeaturesImpl(OtherFeatureRepository otherFeatureRepository, PhoneRepository phoneRepository) {
        this.otherFeatureRepository = otherFeatureRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public OtherFeatures addNewFeature(OtherFeatures otherFeatures, Long phoneId) {
        otherFeatures.setPhone(phoneRepository.findPhoneById(phoneId).get());
        otherFeatureRepository.save(otherFeatures);
        return otherFeatures;
    }

    @Override
    public List<OtherFeatures> getAllFeatures(Long phoneId) {
        return phoneRepository.findPhoneById(phoneId).get().getFeaturesList();
    }

    @Override
    public OtherFeatures getFeatureById(int id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public OtherFeatures updateFeature(OtherFeatures editedOtherFeatures, Long id) {

        OtherFeatures otherFeatures = null;

        if (otherFeatureRepository.findById(id).isPresent()) {
            otherFeatures = otherFeatureRepository.findById(id).get();
            otherFeatures.setFeature(editedOtherFeatures.getFeature());

            otherFeatureRepository.save(otherFeatures);
            return otherFeatures;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Feature with id " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteFeature(Long id) {
        if (otherFeatureRepository.findById(id).isPresent()) {
             otherFeatureRepository.delete(otherFeatureRepository.findById(id).get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Feature rom with id " + id + " not found");
        }
    }
}
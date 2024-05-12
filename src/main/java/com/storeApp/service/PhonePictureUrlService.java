package com.storeApp.service;

import com.storeApp.models.PhonePictureUrl;

import java.util.List;

public interface PhonePictureUrlService {

    PhonePictureUrl addNewPhonePictureUrl(PhonePictureUrl phonePictureUrl, Long phoneId);

    List<PhonePictureUrl> getAllPhonePictureUrls(Long phoneId);

    PhonePictureUrl getPhonePictureUrlById(Long id);

    PhonePictureUrl updatePhonePictureUrl(PhonePictureUrl editedPhonePictureUrl, Long id);

    void deletePhonePictureUrl(Long id);
}
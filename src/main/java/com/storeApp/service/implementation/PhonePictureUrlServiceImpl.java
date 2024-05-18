package com.storeApp.service.implementation;

import com.storeApp.models.PhonePictureUrl;
import com.storeApp.repository.PhonePictureURLRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.PhonePictureUrlService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PhonePictureUrlServiceImpl implements PhonePictureUrlService {

    private final PhonePictureURLRepository phonePictureURLRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhonePictureUrlServiceImpl(PhonePictureURLRepository phonePictureURLRepository, PhoneRepository phoneRepository) {
        this.phonePictureURLRepository = phonePictureURLRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public PhonePictureUrl addNewPhonePictureUrl(PhonePictureUrl phonePictureUrl, Long phoneId) {
        phonePictureUrl.setPhone(phoneRepository.findPhoneById(phoneId).get());
        phonePictureURLRepository.save(phonePictureUrl);
        return phonePictureUrl;
    }

    @Override
    public List<PhonePictureUrl> getAllPhonePictureUrls(Long phoneId) {
        return phoneRepository.findPhoneById(phoneId).get().getPhonePictureURLS();
    }

    @Override
    public PhonePictureUrl getPhonePictureUrlById(Long id) {
        return findPictureUrlByIdOrElseThrow(id);
    }

    @Override
    @Transactional(readOnly = false)
    public PhonePictureUrl updatePhonePictureUrl(PhonePictureUrl editedPhonePictureUrl, Long id) {
        PhonePictureUrl phonePictureUrl = findPictureUrlByIdOrElseThrow(id);
        phonePictureUrl.setUrl(editedPhonePictureUrl.getUrl());
        phonePictureURLRepository.save(phonePictureUrl);
        return phonePictureUrl;
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhonePictureUrl(Long id) {
        phonePictureURLRepository.delete(findPictureUrlByIdOrElseThrow(id));
    }

    private PhonePictureUrl findPictureUrlByIdOrElseThrow(Long pictureId) {
        return phonePictureURLRepository.findById(pictureId).
                orElseThrow(() -> new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone picture URL with id " + pictureId + " not found"));
    }
}
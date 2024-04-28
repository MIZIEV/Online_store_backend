package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.PhonePictureUrl;
import com.storeApp.models.PhoneRom;
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
        if (phonePictureURLRepository.findById(id).isPresent()) {
            return phonePictureURLRepository.findById(id).get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone picture URL with id " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public PhonePictureUrl updatePhonePictureUrl(PhonePictureUrl editedPhonePictureUrl, Long id) {
        PhonePictureUrl phonePictureUrl = null;
        if (phonePictureURLRepository.findById(id).isPresent()) {
            phonePictureUrl = phonePictureURLRepository.findById(id).get();
            phonePictureUrl.setUrl(editedPhonePictureUrl.getUrl());

            phonePictureURLRepository.save(phonePictureUrl);
            return phonePictureUrl;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone picture URL with id " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhonePictureUrl(Long id) {
        if (phonePictureURLRepository.findById(id).isPresent()) {
            phonePictureURLRepository.findById(id).get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone picture URL with id " + id + " not found");
        }
    }
}

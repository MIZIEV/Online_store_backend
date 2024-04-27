package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.PhoneRom;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.repository.PhoneRomRepository;
import com.storeApp.service.PhoneRomService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneRomServiceImpl implements PhoneRomService {

    private final PhoneRomRepository phoneRomRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneRomServiceImpl(PhoneRomRepository phoneRomRepository, PhoneRepository phoneRepository) {
        this.phoneRomRepository = phoneRomRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public PhoneRom addNewPhoneRom(PhoneRom phoneRom,Long id) {
        phoneRom.setPhone(phoneRepository.findPhoneById(id).get());
        phoneRomRepository.save(phoneRom);
        return phoneRom;
    }

    @Override
    public List<PhoneRom> getAllPhonesRoms(Long phoneId) {
        return phoneRepository.findPhoneById(phoneId).get().getRomList();
    }

    @Override
    public PhoneRom getPhoneRomById(Long id) {

        if (phoneRomRepository.findById(id).isPresent()) {
            return phoneRomRepository.findById(id).get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone rom with id " + id + " not found");
        }
    }

    @Override
    public PhoneRom updatePhoneRom(PhoneRom editedPhoneRom, Long id) {

        PhoneRom phoneRom = null;
        if (phoneRomRepository.findById(id).isPresent()) {
            phoneRom = phoneRomRepository.findById(id).get();
            phoneRom.setRomSize(editedPhoneRom.getRomSize());

            phoneRomRepository.save(phoneRom);
            return phoneRom;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone rom with id " + id + " not found");
        }
    }

    @Override
    public void deletePhoneRom(Long id) {
        if (phoneRomRepository.findById(id).isPresent()) {
            phoneRomRepository.delete(phoneRomRepository.findById(id).get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone rom with id " + id + " not found");
        }
    }
}
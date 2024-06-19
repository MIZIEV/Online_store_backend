package com.storeApp.service.implementation;

import com.storeApp.models.PhoneRom;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.repository.PhoneRomRepository;
import com.storeApp.service.PhoneRomService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PhoneRomServiceImpl implements PhoneRomService {

    private final PhoneRomRepository phoneRomRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneRomServiceImpl(PhoneRomRepository phoneRomRepository, PhoneRepository phoneRepository) {
        this.phoneRomRepository = phoneRomRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public PhoneRom addNewPhoneRom(PhoneRom phoneRom, Long id) {
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
        return findPhoneRomByIdOrElseThrow(id);
    }

    @Override
    @Transactional(readOnly = false)
    public PhoneRom updatePhoneRom(PhoneRom editedPhoneRom, Long id) {
        PhoneRom phoneRom = findPhoneRomByIdOrElseThrow(id);
        phoneRom.setRomSize(editedPhoneRom.getRomSize());
        phoneRomRepository.save(phoneRom);
        return phoneRom;
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhoneRom(Long id) {
        phoneRomRepository.delete(findPhoneRomByIdOrElseThrow(id));
    }

    private PhoneRom findPhoneRomByIdOrElseThrow(Long id) {
        return phoneRomRepository.findPhoneRomById(id).
                orElseThrow(() -> new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone rom with id " + id + " not found"));
    }
}
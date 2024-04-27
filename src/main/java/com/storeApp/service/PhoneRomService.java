package com.storeApp.service;

import com.storeApp.models.Phone;
import com.storeApp.models.PhoneRom;

import java.util.List;

public interface PhoneRomService {

    void addNewPhoneRom(PhoneRom phone);

    List<PhoneRom> getAllPhonesRoms();

    PhoneRom getPhoneRomById(Long id);

    void deletePhoneRom(Long id);

    Phone updatePhoneRom(PhoneRom editedPhoneRom, Long id);
}
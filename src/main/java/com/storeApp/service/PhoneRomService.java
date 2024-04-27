package com.storeApp.service;

import com.storeApp.models.PhoneRom;

import java.util.List;

public interface PhoneRomService {

    PhoneRom addNewPhoneRom(PhoneRom phoneRom, Long phoneId);

    List<PhoneRom> getAllPhonesRoms(Long phoneId);

    PhoneRom getPhoneRomById(Long id);

    PhoneRom updatePhoneRom(PhoneRom editedPhoneRom, Long id);

    void deletePhoneRom(Long id);
}
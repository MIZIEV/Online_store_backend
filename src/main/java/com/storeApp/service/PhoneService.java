package com.storeApp.service;

import com.storeApp.models.Phone;

import java.util.List;
import java.util.Set;

public interface PhoneService {

    void addNewPhone(Phone phone);

    List<Phone> getAllPhones(String sort);

    Phone getPhoneById(Long id);

    void deletePhone(Long id);

    Phone updatePhone(Phone editedPhone, Long id);

    void putTheMark(Long id, Double mark);

    String putTheColors(Long id, Set<Long> colorIds);
}
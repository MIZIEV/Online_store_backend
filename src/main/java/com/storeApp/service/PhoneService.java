package com.storeApp.service;

import com.storeApp.models.Category;
import com.storeApp.models.Phone;

import java.util.List;

public interface PhoneService {

    void addNewPhone(Phone phone);

    List<Phone> getAllPhones(String sort, Long categoryId);

    List<Phone> getAllPhonesFilteredByCategory(Category category);

    Phone getPhoneById(Long id);

    void deletePhone(Long id);

    Phone updatePhone(Phone editedPhone, Long id);

    void putTheMark(Long id, Double mark);
}
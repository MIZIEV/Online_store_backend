package com.storeApp.service;

import com.storeApp.models.Phone;
import com.storeApp.models.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PhoneService {

    void addNewPhone(Phone phone);

    List<Phone> getAllPhones(String sort, String brand, String screenSize,
                             Boolean isUsed, String resolution, String ram, String rom,
                             String countOfCores, String countOfSimCard, String price);

    Phone getPhoneById(Long id);

    void deletePhone(Long id);

    Phone updatePhone(Phone editedPhone, Long id);

    String putTheMark(User user, Phone phone, Double mark);

    String addPhoneToWishList(Long phoneId, String email);

    String deletePhoneFromWishList(Long phoneId, String email);

    void calculateAverageRating(Phone phone);

    String putTheColors(Long id, Set<Long> colorIds);

    Map<String, Set<? extends Serializable>> getDistinctValues();
}
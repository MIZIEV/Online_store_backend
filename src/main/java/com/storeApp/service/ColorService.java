package com.storeApp.service;


import com.storeApp.models.Color;

import java.util.List;

public interface ColorService {
    Color addNewColor(Color color);

    List<Color> getAllColors();

    Color getCaseById(Long id);

    Color updateColor(Color editedColor, Long id);

    void deleteColor(Long id);
}
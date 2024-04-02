package com.storeApp.service;


import com.storeApp.models.Color;

import java.util.List;

public interface ColorService {
    Color addNewCase(Color color);

    List<Color> getAllCases();

    Color getCaseById(Long id);

    Color updateCase(Color editedColor, Long id);

    void deleteCase(Long id);
}
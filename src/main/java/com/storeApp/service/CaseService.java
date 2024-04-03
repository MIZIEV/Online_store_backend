package com.storeApp.service;

import com.storeApp.models.Case;
import com.storeApp.models.Color;

import java.util.List;
import java.util.Set;

public interface CaseService {
    Case addNewCase(Case newCase);

    List<Case> getAllCases();

    Case getCaseById(Long id);

    void deleteCase(Long id);

    Case updateCase(Case editedCase, Long id);

    void putTheMark(Long id, Double mark);

    void putTheColors(Long id, Set<Color> colors);
}

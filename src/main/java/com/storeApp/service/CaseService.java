package com.storeApp.service;
import com.storeApp.models.Case;
import java.util.List;

public interface CaseService {
    void addNewCase(Case newCase);
    List<Case> getAllCases(String sort, Long categoryId);
    Case getCaseById(Long id);
    void deleteCase(Long id);
    Case updateCase(Case editedCase, Long id);
    void putTheMark(Long id, Double mark);
}

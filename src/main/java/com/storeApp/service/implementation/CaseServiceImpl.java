package com.storeApp.service.implementation;

import com.storeApp.models.Case;
import com.storeApp.repository.CaseRepository;
import com.storeApp.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public void addNewCase(Case newCase) {

    }

    @Override
    public List<Case> getAllCases(String sort, Long categoryId) {
        return null;
    }

    @Override
    public Case getCaseById(Long id) {
        return null;
    }

    @Override
    public void deleteCase(Long id) {

    }

    @Override
    public Case updateCase(Case editedCase, Long id) {
        return null;
    }

    @Override
    public void putTheMark(Long id, Double mark) {

    }
}
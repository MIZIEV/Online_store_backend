package com.storeApp.service;

import com.storeApp.models.Description;

import java.util.List;

public interface DescriptionService {

    Description addNewDescription(Long phoneId,Description description);

    List<Description> getAllDescriptions(Long phoneId);

    Description getDescriptionById(Long id);

    Description updateDescription(Description editedDescription, Long id);

    void deleteDescription(Long id);
}
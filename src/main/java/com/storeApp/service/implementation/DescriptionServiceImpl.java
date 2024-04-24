package com.storeApp.service.implementation;

import com.storeApp.models.Description;
import com.storeApp.repository.DescriptionRepository;
import com.storeApp.service.DescriptionService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }


    @Override
    public Description addNewDescription(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public List<Description> getAllDescriptions() {
        return descriptionRepository.findAll();
    }

    @Override
    public Description getDescriptionById(Long id) {

        Description description = null;

        if (descriptionRepository.findById(id).isPresent()) {
            description = descriptionRepository.findById(id).get();
            return description;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Description with id - " + id + " not found");
        }
    }

    @Override
    public Description updateDescription(Description editedDescription, Long id) {

        Description description = null;
        if (descriptionRepository.findById(id).isPresent()) {
            description = descriptionRepository.findById(id).get();

            description.setDescriptionText(editedDescription.getDescriptionText());
            return description;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Description with id - " + id + " not found");
        }
    }

    @Override
    public void deleteDescription(Long id) {
        Description description = null;

        if (descriptionRepository.findById(id).isPresent()) {
            descriptionRepository.delete(descriptionRepository.findById(id).get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Description with id - " + id + " not found");
        }
    }
}

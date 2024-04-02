package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.PhoneService;

import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public List<Phone> getAllPhones(String sort, Long categoryId) {

        List<Phone> phones = null;

        if (categoryId != null) {
            // phones = getAllPhonesFilteredByCategory(categoryRepository.findCategoryById(categoryId).get());
        } else {
            phones = phoneRepository.findAll();
        }
        if ("minPrice".equalsIgnoreCase(sort)) {
            phones.sort(Comparator.comparing(Phone::getPrice));
        } else if ("maxPrice".equalsIgnoreCase(sort)) {
            phones.sort(Comparator.comparing(Phone::getPrice).reversed());
        } else if ("maxRating".equalsIgnoreCase(sort)) {
            phones.sort(Comparator.comparing(Phone::getRating).reversed());
        }

        return phones;
    }


    @Override
    public Phone getPhoneById(Long id) {

        if (phoneRepository.findProductById(id).isPresent()) {
            return phoneRepository.findProductById(id).get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void putTheMark(Long id, Double mark) {

        if (phoneRepository.findProductById(id).isPresent()) {

            Phone phone = phoneRepository.findProductById(id).get();
            Double currentRating = null;
            Long voteCount = null;

            if (phone.getVoteCount() == null) {
                phone.setRating(0.0);
                phone.setVoteCount(0L);
            }

            currentRating = phone.getRating() * phone.getVoteCount();
            voteCount = phone.getVoteCount() + 1;


            phone.setVoteCount(voteCount);
            phone.setRating((currentRating + mark) / voteCount);

            phoneRepository.save(phone);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Phone updatePhone(Phone editedPhone, Long id) {

        Phone phoneForUpdating = null;

        if (phoneRepository.findProductById(id).isPresent()) {
            phoneForUpdating = phoneRepository.findProductById(id).get();

            phoneForUpdating.setId(id);
            phoneForUpdating.setModel(editedPhone.getModel());
            phoneForUpdating.setMainPictureURL(editedPhone.getMainPictureURL());
            phoneForUpdating.setOs(editedPhone.getOs());
            phoneForUpdating.setOsVersion(editedPhone.getOsVersion());
            phoneForUpdating.setScreenSize(editedPhone.getScreenSize());
            phoneForUpdating.setResolution(editedPhone.getResolution());
            phoneForUpdating.setMainCamera(editedPhone.getMainCamera());
            phoneForUpdating.setFrontCamera(editedPhone.getFrontCamera());
            phoneForUpdating.setProcessor(editedPhone.getProcessor());
            phoneForUpdating.setCountOfCores(editedPhone.getCountOfCores());
            phoneForUpdating.setRam(editedPhone.getRam());
            phoneForUpdating.setRom(editedPhone.getRom());
            phoneForUpdating.setWeight(editedPhone.getWeight());
            phoneForUpdating.setBatteryCapacity(editedPhone.getBatteryCapacity());
            phoneForUpdating.setCountOfSimCard(editedPhone.getCountOfSimCard());
            phoneForUpdating.setPrice(editedPhone.getPrice());
            phoneForUpdating.setRating(editedPhone.getRating());
            phoneForUpdating.setVoteCount(editedPhone.getVoteCount());
            phoneForUpdating.setDescription(editedPhone.getDescription());
            phoneForUpdating.setBrand(editedPhone.getBrand());
            phoneForUpdating.setUsed(editedPhone.isUsed());
            phoneForUpdating.setPhonePictureURLS(editedPhone.getPhonePictureURLS());
            phoneForUpdating.setStandardList(editedPhone.getStandardList());
            phoneForUpdating.setFeaturesList(editedPhone.getFeaturesList());

            phoneRepository.save(phoneForUpdating);
            return phoneForUpdating;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhone(Long id) {

        Phone phone = null;

        if (phoneRepository.findProductById(id).isPresent()) {
            phone = phoneRepository.findProductById(id).get();
            phoneRepository.delete(phone);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }
}
package com.storeApp.service.implementation;

import com.storeApp.models.Case;
import com.storeApp.models.Color;
import com.storeApp.models.Phone;
import com.storeApp.models.PhoneRom;
import com.storeApp.repository.ColorRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.repository.PhoneRomRepository;
import com.storeApp.service.PhoneService;

import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final ColorRepository colorRepository;
    private final PhoneRomRepository phoneRomRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, ColorRepository colorRepository, PhoneRomRepository phoneRomRepository) {
        this.phoneRepository = phoneRepository;
        this.colorRepository = colorRepository;
        this.phoneRomRepository = phoneRomRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewPhone(Phone phone) {
//todo why simple saving phone entity with romList doesn't save ref. between them
        phone.setRomList(phone.getRomList());
        for (PhoneRom element : phone.getRomList()) {
            element.setPhone(phone);
            phoneRomRepository.save(element);
        }
        phoneRepository.save(phone);
    }

    @Override
    public List<Phone> getAllPhones(String sort) {

        List<Phone> phones = phoneRepository.findAll();

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

        if (phoneRepository.findPhoneById(id).isPresent()) {
            return phoneRepository.findPhoneById(id).get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void putTheMark(Long id, Double mark) {

        if (phoneRepository.findPhoneById(id).isPresent()) {

            Phone phone = phoneRepository.findPhoneById(id).get();
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

        if (phoneRepository.findPhoneById(id).isPresent()) {
            phoneForUpdating = phoneRepository.findPhoneById(id).get();

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
            phoneForUpdating.setWeight(editedPhone.getWeight());
            phoneForUpdating.setBatteryCapacity(editedPhone.getBatteryCapacity());
            phoneForUpdating.setCountOfSimCard(editedPhone.getCountOfSimCard());
            phoneForUpdating.setPrice(editedPhone.getPrice());
            phoneForUpdating.setRating(editedPhone.getRating());
            phoneForUpdating.setVoteCount(editedPhone.getVoteCount());
            phoneForUpdating.setBrand(editedPhone.getBrand());
            phoneForUpdating.setUsed(editedPhone.isUsed());
            phoneForUpdating.setPhonePictureURLS(editedPhone.getPhonePictureURLS());
            phoneForUpdating.setStandardList(editedPhone.getStandardList());
            phoneForUpdating.setFeaturesList(editedPhone.getFeaturesList());
            phoneRepository.save(phoneForUpdating);

            for (PhoneRom element : editedPhone.getRomList()) {
                element.setPhone(phoneForUpdating);
                phoneRomRepository.save(element);
            }

            return phoneForUpdating;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public String putTheColors(Long id, Set<Long> colorIds) {
        if (phoneRepository.findPhoneById(id).isPresent()) {

            Phone phone = phoneRepository.findPhoneById(id).get();
            Set<Color> colors = colorRepository.findByColorsIds(colorIds);

            Set<Long> existingColorIds = colors.stream().map(Color::getId).collect(Collectors.toSet());
            Set<Long> nonExistingColorIds = colorIds.stream()
                    .filter(colorId -> !existingColorIds.contains(colorId))
                    .collect(Collectors.toSet());

            if (colors.isEmpty()) {
                throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                        "Colors with id - " + nonExistingColorIds + " not found!");
            } else if (nonExistingColorIds.isEmpty()) {
                phone.setColors(colors);
                phoneRepository.save(phone);
                return "colors with id" + existingColorIds + " is putted! ";
            } else {
                phone.setColors(colors);
                phoneRepository.save(phone);
                return "Color with id" + existingColorIds + " is putted!\n" +
                        "Color with id" + nonExistingColorIds + " not found!";
            }
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhone(Long id) {

        Phone phone = null;

        if (phoneRepository.findPhoneById(id).isPresent()) {
            phone = phoneRepository.findPhoneById(id).get();
            phoneRepository.delete(phone);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found!");
        }
    }
}
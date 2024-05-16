package com.storeApp.service.implementation;

import com.storeApp.models.*;
import com.storeApp.repository.*;
import com.storeApp.service.PhoneService;

import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneRatingRepository phoneRatingRepository;
    private final ColorRepository colorRepository;
    private final PhoneRomRepository phoneRomRepository;
    private final MobileCommunicationStandardRepository mobileCommunicationStandardRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneRatingRepository phoneRatingRepository, ColorRepository colorRepository, PhoneRomRepository phoneRomRepository, MobileCommunicationStandardRepository mobileCommunicationStandardRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneRatingRepository = phoneRatingRepository;
        this.colorRepository = colorRepository;
        this.phoneRomRepository = phoneRomRepository;
        this.mobileCommunicationStandardRepository = mobileCommunicationStandardRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewPhone(Phone phone) {
        //todo why simple saving phone entity with romList doesn't save ref. between them
        phone.setRomList(phone.getRomList());
        phone.setCommunicationStandardList(phone.getCommunicationStandardList());

        if (phone.getRomList() != null) {
            for (PhoneRom element : phone.getRomList()) {
                element.setPhone(phone);
                phoneRomRepository.save(element);
            }
        }

        for (MobileCommunicationStandard element : phone.getCommunicationStandardList()) {
            element.setPhone(phone);
            mobileCommunicationStandardRepository.save(element);
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
            //todo  change logic phones.sort(Comparator.comparing(Phone::getRating).reversed());
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
    public String putTheMark(User user, Phone phone, Double mark) {

        if (phone.getRatings().stream().anyMatch(r -> r.getUser().equals(user))) {
            return "User already putted the mark to this phone!";
        }

        PhoneRating rating = new PhoneRating();
        rating.setPhone(phone);
        rating.setUser(user);
        rating.setRating(mark);

        if (phone.getVoteCount() == null) {
            phone.setVoteCount(0L);
        }
        phone.setVoteCount(phone.getVoteCount() + 1);

        phoneRepository.save(phone);
        phoneRatingRepository.save(rating);

        return "Mark - " + mark + " was putted to phone with id - " + phone.getId();
    }

    public void calculateAverageRating(Phone phone) {
        List<PhoneRating> ratings = phone.getRatings();
        if (ratings != null && !ratings.isEmpty()) {
            double totalRating = 0;
            for (PhoneRating rating : ratings) {
                totalRating += rating.getRating();
            }
            double averageRating = totalRating / ratings.size();
            phone.setRating(averageRating);
        } else {
            phone.setRating(0.0);
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
            //phoneForUpdating.setRating(editedPhone.getRating());
            phoneForUpdating.setVoteCount(editedPhone.getVoteCount());
            phoneForUpdating.setBrand(editedPhone.getBrand());
            phoneForUpdating.setUsed(editedPhone.isUsed());
            phoneForUpdating.setPhonePictureURLS(editedPhone.getPhonePictureURLS());
            phoneForUpdating.setCommunicationStandardList(editedPhone.getCommunicationStandardList());
            phoneForUpdating.setFeaturesList(editedPhone.getFeaturesList());
            phoneRepository.save(phoneForUpdating);

            if (editedPhone.getRomList() != null) {
                for (PhoneRom element : editedPhone.getRomList()) {
                    element.setPhone(phoneForUpdating);
                    phoneRomRepository.save(element);
                }
            }

            if (editedPhone.getCommunicationStandardList() != null) {
                for (MobileCommunicationStandard element : editedPhone.getCommunicationStandardList()) {
                    element.setPhone(phoneForUpdating);
                    mobileCommunicationStandardRepository.save(element);
                }
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

    @Override
    public Map<String, Set<? extends Serializable>> getDistinctValues(){
        Map<String, Set<? extends Serializable>> distinctValuesMap = new LinkedHashMap<>();

        distinctValuesMap.put("screenSize", phoneRepository.findDistinctScreenSize());
        distinctValuesMap.put("resolution", phoneRepository.findDistinctResolution());
        distinctValuesMap.put("processor", phoneRepository.findDistinctProcessor());
        distinctValuesMap.put("countOfCores", phoneRepository.findDistinctCountOfCores());
        distinctValuesMap.put("ram", phoneRepository.findDistinctRam());
        distinctValuesMap.put("batteryCapacity", phoneRepository.findDistinctBatteryCapacity());
        distinctValuesMap.put("countOfSimCard", phoneRepository.findDistinctCountOfSimCard());

        return distinctValuesMap;
    }
}
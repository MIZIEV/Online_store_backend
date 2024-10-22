package com.storeApp.service.implementation;

import com.storeApp.models.*;
import com.storeApp.models.phone.Phone;
import com.storeApp.repository.*;
import com.storeApp.service.PhoneService;

import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneRatingRepository phoneRatingRepository;
    private final ColorRepository colorRepository;
    private final PhoneRomRepository phoneRomRepository;
    private final MobileCommunicationStandardRepository mobileCommunicationStandardRepository;
    private final UserRepository userRepository;
    private final SelectedPhoneRepository selectedPhoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneRatingRepository phoneRatingRepository, ColorRepository colorRepository, PhoneRomRepository phoneRomRepository, MobileCommunicationStandardRepository mobileCommunicationStandardRepository, UserRepository userRepository, SelectedPhoneRepository selectedPhoneRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneRatingRepository = phoneRatingRepository;
        this.colorRepository = colorRepository;
        this.phoneRomRepository = phoneRomRepository;
        this.mobileCommunicationStandardRepository = mobileCommunicationStandardRepository;
        this.userRepository = userRepository;
        this.selectedPhoneRepository = selectedPhoneRepository;
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
    public List<Phone> getAllPhones(String sort, String searchTerm, String brand, String screenSize,
                                    Boolean isUsed, String resolution, String ram, String rom,
                                    String countOfCores, String countOfSimCard, String price) {

        List<Phone> phones = phoneRepository.findAll();

        if (searchTerm == null) {
            phones = phoneRepository.findAll();
        } else if (searchTerm != null && !searchTerm.isEmpty() && !searchTerm.equals("all")) {
            phones = filterPhonesBySearchTerm(phones, searchTerm);
        } else if (searchTerm.equals("all")) {
            phones = phoneRepository.findAll();
        }

        if ("minPrice".equalsIgnoreCase(sort)) {
            phones.sort(Comparator.comparing(Phone::getPrice));
        } else if ("maxPrice".equalsIgnoreCase(sort)) {
            phones.sort(Comparator.comparing(Phone::getPrice).reversed());
        } else if ("maxRating".equalsIgnoreCase(sort)) {
            //todo  change logic phones.sort(Comparator.comparing(Phone::getRating).reversed());
        }

        if (brand != null && !brand.isEmpty()) {
            phones = phones.stream().
                    filter(phone -> Arrays.stream(brand.split(",")).
                            anyMatch(selectedBrand -> selectedBrand.trim().
                                    equalsIgnoreCase(phone.getBrand().toString()))).
                    collect(Collectors.toList());
        }

        if (screenSize != null && !screenSize.isEmpty()) {
            String[] screenSizes = screenSize.split(",");
            phones = phones.stream()
                    .filter(phone -> Arrays.stream(screenSizes)
                            .anyMatch(sizeRange -> {
                                String[] range = sizeRange.trim().split("-");
                                if (range.length == 2) {
                                    try {
                                        double minSize = Double.parseDouble(range[0]);
                                        double maxSize = Double.parseDouble(range[1]);
                                        return phone.getScreenSize() >= minSize && phone.getScreenSize() <= maxSize;
                                    } catch (NumberFormatException e) {
                                        return false;
                                    }
                                } else {
                                    try {
                                        double screenSizeValue = Double.parseDouble(sizeRange.trim());
                                        return screenSizeValue == phone.getScreenSize();
                                    } catch (NumberFormatException e) {
                                        return false;
                                    }
                                }
                            }))
                    .collect(Collectors.toList());
        }

        if (isUsed != null) {
            phones = phones.stream()
                    .filter(phone -> phone.isUsed() == isUsed)
                    .collect(Collectors.toList());
        }
        if (resolution != null && !resolution.isEmpty()) {
            phones = phones.stream().
                    filter(phone -> Arrays.stream(resolution.split(",")).
                            anyMatch(selectedBrand -> selectedBrand.trim().
                                    equalsIgnoreCase(phone.getResolution().toString()))).
                    collect(Collectors.toList());
        }

        if (price != null && !price.isEmpty()) {
            String[] prices = price.split(",");
            phones = phones.stream()
                    .filter(phone -> Arrays.stream(prices)
                            .anyMatch(priceRange -> {
                                String[] range = priceRange.trim().split("-");
                                if (range.length == 2) {
                                    try {
                                        double minPrice = Double.parseDouble(range[0]);
                                        double maxPrice = Double.parseDouble(range[1]);
                                        return phone.getPrice() >= minPrice && phone.getPrice() <= maxPrice;
                                    } catch (NumberFormatException e) {
                                        return false;
                                    }
                                } else {
                                    try {
                                        double priceValue = Double.parseDouble(priceRange.trim());
                                        return priceValue == phone.getPrice();
                                    } catch (NumberFormatException e) {
                                        return false;
                                    }
                                }
                            }))
                    .collect(Collectors.toList());
        }

        if (rom != null && !rom.isEmpty()) {
            Set<Short> romSizes = Arrays.stream(rom.split(","))
                    .map(String::trim)
                    .map(Short::parseShort)
                    .collect(Collectors.toSet());
            phones = phones.stream()
                    .filter(phone -> phone.getRomList().stream()
                            .anyMatch(phoneRom -> romSizes.contains(phoneRom.getRomSize())))
                    .collect(Collectors.toList());
        }



        phones = applyNumericFilter(phones, ram, Phone::getRam, Short::parseShort);
        phones = applyNumericFilter(phones, countOfCores, Phone::getCountOfCores, Byte::parseByte);
        phones = applyNumericFilter(phones, countOfSimCard, Phone::getCountOfSimCard, Byte::parseByte);

        return phones;
    }

    @Override
    public Phone getPhoneById(Long id) {
        return findPhoneByIdOrThrow(id);
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

    @Override
    @Transactional(readOnly = true)
    public boolean hasUserRatedPhone(User user, Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone not found"));
        return phone.getRatings().stream().anyMatch(r -> r.getUser().equals(user));
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

        Phone phoneForUpdating = findPhoneByIdOrThrow(id);

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
        phoneForUpdating.setVoteCount(editedPhone.getVoteCount());
        phoneForUpdating.setBrand(editedPhone.getBrand());
        phoneForUpdating.setUsed(editedPhone.isUsed());
        phoneForUpdating.setProducingCountry(editedPhone.getProducingCountry());
        phoneForUpdating.setPhonePictureURLS(editedPhone.getPhonePictureURLS());
        phoneForUpdating.setFeaturesList(editedPhone.getFeaturesList());

        phoneRepository.save(phoneForUpdating);

        if (editedPhone.getRomList() != null) {
           // selectedPhoneRepository.deleteAllByRomIdIn(phoneForUpdating.getRomList().stream().map(PhoneRom::getId).collect(Collectors.toList()));

            phoneRomRepository.deleteAllByPhoneId(id);

            for (PhoneRom element : editedPhone.getRomList()) {
                element.setPhone(phoneForUpdating);
                phoneRomRepository.save(element);
            }
        }

        if (editedPhone.getCommunicationStandardList() != null) {

            mobileCommunicationStandardRepository.deleteAllByPhoneId(id);

            for (MobileCommunicationStandard element : editedPhone.getCommunicationStandardList()) {
                element.setPhone(phoneForUpdating);
                mobileCommunicationStandardRepository.save(element);
            }
        }

        phoneRepository.save(phoneForUpdating);
        return phoneForUpdating;
    }

    @Override
    @Transactional(readOnly = false)
    public String putTheColors(Long id, Set<Long> colorIds) {

        Phone phone = findPhoneByIdOrThrow(id);
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
    }

    @Override
    @Transactional(readOnly = false)
    public String deletePhoneFromWishList(Long phoneId, String email) {
        Phone phone = null;
        User user = null;

        if (userRepository.findByEmail(email).isPresent() && phoneRepository.findPhoneById(phoneId).isPresent()) {
            user = userRepository.findByEmail(email).get();
            phone = phoneRepository.findPhoneById(phoneId).get();

            if (user.getWishList().contains(phone)) {
                user.getWishList().remove(phone);
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("User or Phone not found");
            }
        }
        return "Phone with id - " + phoneId + " was deleted from wishList to user with username - " + email;
    }

    @Override
    @Transactional(readOnly = false)
    public String addPhoneToWishList(Long phoneId, String email) {
        Phone phone = null;
        User user = null;

        if (userRepository.findByEmail(email).isPresent() && phoneRepository.findPhoneById(phoneId).isPresent()) {
            user = userRepository.findByEmail(email).get();
            phone = phoneRepository.findPhoneById(phoneId).get();

            user.getWishList().add(phone);
            userRepository.save(user);
        }
        return "Phone with id - " + phoneId + " was added to wishList to user with username - " + email;
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePhone(Long id) {
        Phone phone = findPhoneByIdOrThrow(id);
        phoneRepository.delete(phone);
    }

    @Override
    public Map<String, Set<? extends Serializable>> getDistinctValues() {
        Map<String, Set<? extends Serializable>> distinctValuesMap = new LinkedHashMap<>();

        distinctValuesMap.put("screenSize", phoneRepository.findDistinctScreenSize());
        distinctValuesMap.put("resolution", phoneRepository.findDistinctResolution());
        distinctValuesMap.put("processor", phoneRepository.findDistinctProcessor());
        distinctValuesMap.put("countOfCores", phoneRepository.findDistinctCountOfCores());
        distinctValuesMap.put("ram", phoneRepository.findDistinctRam());
        distinctValuesMap.put("batteryCapacity", phoneRepository.findDistinctBatteryCapacity());
        distinctValuesMap.put("countOfSimCard", phoneRepository.findDistinctCountOfSimCard());
        distinctValuesMap.put("rom", phoneRomRepository.findDistinctPhoneRom());

        return distinctValuesMap;
    }

    private <T extends Number> List<Phone> applyNumericFilter(List<Phone> phones, String filter,
                                                              Function<Phone, T> getter, Function<String, T> parser) {
        if (filter == null || filter.isEmpty()) {
            return phones;
        }
        String[] filterValue = filter.split(",");

        return phones.stream()
                .filter(phone -> Arrays.stream(filterValue)
                        .anyMatch(value -> {
                            try {
                                T parsedValue = parser.apply(value.trim());
                                return parsedValue.equals(getter.apply(phone));
                            } catch (NumberFormatException e) {
                                return false;
                            }
                        }))
                .collect(Collectors.toList());
    }

    private Phone findPhoneByIdOrThrow(Long id) {
        return phoneRepository.findPhoneById(id).
                orElseThrow(() -> new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + id + " not found"));
    }

    private static boolean containsAllWord(String text, String... words) {
        for (String word : words) {
            if (!text.toLowerCase().contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private List<Phone> filterPhonesBySearchTerm(List<Phone> phones, String searchTerm) {
        List<Phone> filteredList = new ArrayList<>();
        String[] searchTerms = searchTerm.split("\\s+");

        for (Phone phone : phones) {
            String phoneDetails = phone.getBrand() + " " + phone.getModel();
            if (containsAllWord(phoneDetails, searchTerms)) {
                filteredList.add(phone);
            }
        }

        return filteredList;
    }
}
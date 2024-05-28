package com.storeApp.controllers;

import com.storeApp.dto.PhoneDto;
import com.storeApp.dto.PhoneRatingDto;
import com.storeApp.models.Phone;
import com.storeApp.models.PhoneRating;
import com.storeApp.models.User;
import com.storeApp.repository.UserRepository;
import com.storeApp.security.CustomUserDetailService;
import com.storeApp.service.PhoneService;
import com.storeApp.util.exception.OnlineStoreApiException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin("*")
public class PhoneController {

    private final PhoneService phoneService;
    private final UserRepository userRepository;

    @Autowired
    public PhoneController(PhoneService phoneService, CustomUserDetailService customUserDetailService, UserRepository userRepository) {
        this.phoneService = phoneService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewPhone(@Valid @RequestBody PhoneDto phoneDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error:\n");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";\n");
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        } else {
            Phone phone = convertToProduct(phoneDto);
            phoneService.addNewPhone(phone);
            return new ResponseEntity<>(phoneDto, HttpStatus.CREATED);
        }
    }

    @GetMapping("/list")
    public List<Phone> getAllPhones(@RequestParam(name = "sort", defaultValue = "asc") String sort,
                                    @RequestParam(name = "searchTerm", required = false) String searchTerm,
                                    @RequestParam(name = "brand", required = false) String brand,
                                    @RequestParam(name = "screenSize", required = false) String screenSize,
                                    @RequestParam(name = "isUsed", required = false) Boolean isUsed,
                                    @RequestParam(name = "resolution", required = false) String resolution,
                                    @RequestParam(name = "ram", required = false) String ram,
                                    @RequestParam(name = "rom", required = false) String rom,
                                    @RequestParam(name = "countOfCores", required = false) String countOfCores,
                                    @RequestParam(name = "countOfSimCard", required = false) String countOfSimCard,
                                    @RequestParam(name = "price", required = false) String price) {

        /*List<Phone> filteredList = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            String[] searchTerms = searchTerm.split("\\s+");

            List<Phone> phoneList = null;

            phoneList = phoneService.getAllPhones(sort);

            for (Phone element : phoneList) {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(element.getBrand()).append(" ").append(element.getModel());

                if (containsAllWord(stringBuffer.toString(), searchTerms)) {
                    filteredList.add(element);
                }
            }

            return filteredList;
        } else if (sort == null && searchTerm == null) {
            return filteredList;
        } else {
            filteredList = phoneService.getAllPhones(sort);
        }
        return filteredList;*/
        return phoneService.getAllPhones(sort, brand, screenSize, isUsed,
                resolution, ram, rom, countOfCores, countOfSimCard, price);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhoneById(@PathVariable("id") long id) {
        Phone phone = phoneService.getPhoneById(id);

        phoneService.calculateAverageRating(phone);
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> putTheMark(@PathVariable("id") Long id,
                                        @RequestBody PhoneRatingDto ratingDto) {

        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Phone phone = phoneService.getPhoneById(id);
        User user = userRepository.findByPhoneNumberOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new OnlineStoreApiException(HttpStatus.BAD_REQUEST, "User not found"));

        return new ResponseEntity<>(phoneService.putTheMark(user, phone, convertToPhoneRating(ratingDto).getRating()),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}/color")
    public ResponseEntity<?> putTheColor(@PathVariable("id") Long id, @RequestBody Set<Long> colorIds) {
        return new ResponseEntity<>(phoneService.putTheColors(id, colorIds), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePhone(@RequestBody Phone editedPhone, @PathVariable Long id) {
        return new ResponseEntity<>(phoneService.updatePhone(editedPhone, id), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    //PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deletePhone(@PathVariable("id") long id) {
        phoneService.deletePhone(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/distinct-characteristics")
    public ResponseEntity<?> getDistinctValues() {
        return new ResponseEntity<>(phoneService.getDistinctValues(), HttpStatus.OK);
    }

    @PatchMapping("/{id}/wishList/{username}/add")
    public ResponseEntity<?> addPhoneToWishList(@PathVariable("id") Long id,
                                                @PathVariable("username") String username) {

        return new ResponseEntity<>(phoneService.addPhoneToWishList(id, username), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/wishList/{username}/remove")
    public ResponseEntity<?> deletePhoneFromWishList(@PathVariable("id") Long id,
                                                     @PathVariable("username") String username) {

        return new ResponseEntity<>(phoneService.deletePhoneFromWishList(id, username), HttpStatus.OK);
    }

    private PhoneRating convertToPhoneRating(PhoneRatingDto phoneRatingDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(phoneRatingDto, PhoneRating.class);
    }

    private Phone convertToProduct(PhoneDto phoneDto) {

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(phoneDto, Phone.class);
    }

    private static boolean containsAllWord(String text, String... words) {
        for (String word : words) {
            if (!text.toLowerCase().contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
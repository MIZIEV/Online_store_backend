package com.storeApp.controllers;

import com.storeApp.dto.PhoneDto;
import com.storeApp.models.Phone;
import com.storeApp.service.PhoneService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin("*")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
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
            phoneService.addNewPhone(convertToProduct(phoneDto));
            return new ResponseEntity<>(phoneDto, HttpStatus.CREATED);
        }
    }

    @GetMapping("/list")
    public List<Phone> getAllPhones(@RequestParam(name = "sort", defaultValue = "asc") String sort,
                                    @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        List<Phone> filteredList = new ArrayList<>();

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
        return filteredList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") long id) {
        return new ResponseEntity<>(phoneService.getPhoneById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> putTheMark(@PathVariable("id") Long id, @RequestBody PhoneDto phoneDto) {

        phoneService.putTheMark(id, phoneDto.getRating());

        return new ResponseEntity<>(HttpStatus.OK);
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
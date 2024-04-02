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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin("*")
public class PhoneController {

    private final PhoneService productService;

    @Autowired
    public PhoneController(PhoneService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewPhone(@Valid @RequestBody PhoneDto phoneDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error: ");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";");
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        } else {
            productService.addNewPhone(convertToProduct(phoneDto));
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

            phoneList = productService.getAllPhones(sort);

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
            filteredList = productService.getAllPhones(sort);
        }
        return filteredList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.getPhoneById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> putTheMark(@PathVariable("id") Long id, @RequestBody PhoneDto phoneDto) {

        productService.putTheMark(id, phoneDto.getRating());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePhone(@RequestBody Phone editedPhone, @PathVariable Long id) {
        return new ResponseEntity<>(productService.updatePhone(editedPhone, id), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    //PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deletePhone(@PathVariable("id") long id) {
        productService.deletePhone(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Phone convertToProduct(PhoneDto phoneDto) {

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(phoneDto, Phone.class);

    }

    private PhoneDto convertToDto(Phone phone) {

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(phone, PhoneDto.class);
    }

    private List<PhoneDto> convertListToDto(List<Phone> phoneList) {

        List<PhoneDto> convertedList = new ArrayList<>();

        for (Phone phone : phoneList) {
            convertedList.add(convertToDto(phone));
        }

        return phoneList.stream().map(this::convertToDto).collect(Collectors.toList());
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
package com.storeApp.controllers;

import com.storeApp.dto.PhonePictureUrlDto;
import com.storeApp.models.PhonePictureUrl;
import com.storeApp.service.PhonePictureUrlService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/phone/{id}/pctureUrl")
public class PhonePictureUrlController {

    private final PhonePictureUrlService phonePictureUrlService;

    @Autowired
    public PhonePictureUrlController(PhonePictureUrlService phonePictureUrlService) {
        this.phonePictureUrlService = phonePictureUrlService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewPictureUrl(@RequestBody PhonePictureUrlDto phonePictureUrlDto,
                                              @PathVariable("id") Long phoneId) {
        return new ResponseEntity<>(phonePictureUrlService.
                addNewPhonePictureUrl(convertToPhonePictureUrl(phonePictureUrlDto), phoneId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllPictureUrls(@PathVariable("id") Long phoneId) {
        return new ResponseEntity<>(phonePictureUrlService.getAllPhonePictureUrls(phoneId), HttpStatus.OK);
    }

    @GetMapping("/{pictureId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getPhonePictureUrlById(@PathVariable("pictureId") Long pictureId) {
        return new ResponseEntity<>(phonePictureUrlService.getPhonePictureUrlById(pictureId), HttpStatus.OK);
    }

    @PutMapping("/{pictureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePhonePictureUrl(@PathVariable("pictureId") Long pictureId,
                                                   @RequestBody PhonePictureUrl editedPhonePictureUrl) {
        return new ResponseEntity<>(phonePictureUrlService.updatePhonePictureUrl(editedPhonePictureUrl, pictureId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{pictureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePhonePictureUrl(@PathVariable("pictureId") Long pictureId) {
        phonePictureUrlService.deletePhonePictureUrl(pictureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private PhonePictureUrl convertToPhonePictureUrl(PhonePictureUrlDto phonePictureUrlDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(phonePictureUrlDto, PhonePictureUrl.class);
    }
}
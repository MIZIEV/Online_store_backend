package com.storeApp.controllers;

import com.storeApp.models.PhoneRom;
import com.storeApp.service.PhoneRomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/phone/{id}/rom")
public class PhoneRomController {

    private final PhoneRomService phoneRomService;

    @Autowired
    public PhoneRomController(PhoneRomService phoneRomService) {
        this.phoneRomService = phoneRomService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewPhoneRom(@PathVariable("id") Long phoneId, @RequestBody PhoneRom phoneRom) {
        return new ResponseEntity<>(phoneRomService.addNewPhoneRom(phoneRom, phoneId), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllPhoneRoms(@PathVariable("id") Long phoneId) {
        return new ResponseEntity<>(phoneRomService.getAllPhonesRoms(phoneId), HttpStatus.OK);
    }

    @GetMapping("/{romId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getPhoneRomById(@PathVariable("romId") Long id) {
        return new ResponseEntity<>(phoneRomService.getPhoneRomById(id), HttpStatus.OK);
    }

    @PutMapping("/{romId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePhoneRom(@PathVariable("romId") Long id, @RequestBody PhoneRom editedPhoneRom) {
        return new ResponseEntity<>(phoneRomService, HttpStatus.OK);
    }

    @DeleteMapping("/{romId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePhoneRom(@PathVariable("romId") Long id) {
        phoneRomService.deletePhoneRom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package com.storeApp.controllers;

import com.storeApp.dto.DescriptionDto;
import com.storeApp.models.Description;
import com.storeApp.service.DescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/phone/{id}/description")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @Autowired
    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewDescription(@PathVariable("id") Long phoneId,
                                               @RequestBody DescriptionDto descriptionDto) {
        Description newDescription = descriptionService.addNewDescription(phoneId,
                convertToDescription(descriptionDto));
        return new ResponseEntity<>(newDescription, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllDescriptions(@PathVariable("id") Long phoneId) {
        return new ResponseEntity<>(descriptionService.getAllDescriptions(phoneId), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{descriptionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDescription(@PathVariable("descriptionId") Long descriptionId){
        descriptionService.deleteDescription(descriptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Description convertToDescription(DescriptionDto descriptionDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(descriptionDto, Description.class);
    }
}

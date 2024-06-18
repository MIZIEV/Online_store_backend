package com.storeApp.controllers;

import com.storeApp.dto.OtherFeaturesDto;
import com.storeApp.models.OtherFeatures;
import com.storeApp.service.OtherFeaturesService;
import com.storeApp.service.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/phone/{id}/feature")
public class OtherFeatureController {

    private final OtherFeaturesService otherFeaturesService;

    @Autowired
    public OtherFeatureController(OtherFeaturesService otherFeaturesService) {
        this.otherFeaturesService = otherFeaturesService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> addNewFeature(@PathVariable("id") Long phoneId,
                                           @RequestBody OtherFeaturesDto otherFeaturesDto) {
        return new ResponseEntity<>(otherFeaturesService.
                addNewFeature(convertToOtherFeature(otherFeaturesDto), phoneId),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCharacteristics(@PathVariable("id") Long phoneId) {
        return new ResponseEntity<>(otherFeaturesService.getAllFeatures(phoneId),HttpStatus.OK);
    }



    @PutMapping("/{featureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCharacteristic(@RequestBody OtherFeaturesDto editedFeatureDto,
                                                  @PathVariable("featureId") Long id) {
        OtherFeatures updatedCharacteristic =
                otherFeaturesService.updateFeature(convertToOtherFeature(editedFeatureDto), id);

        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }

    @DeleteMapping("/{featureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("featureId") Long id) {
        otherFeaturesService.deleteFeature(id);
        return new ResponseEntity<>("Feature deleted successfully.", HttpStatus.OK);
    }

    private OtherFeatures convertToOtherFeature(OtherFeaturesDto otherFeaturesDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(otherFeaturesDto, OtherFeatures.class);
    }
}
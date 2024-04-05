package com.storeApp.controllers;

import com.storeApp.dto.ProductCharacteristicDto;
import com.storeApp.models.OtherFeatures;
import com.storeApp.service.PhoneOtherFeaturesService;
import com.storeApp.service.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/product/{product_id}/characteristic")
public class OtherFeatureController {

    private final PhoneOtherFeaturesService phoneOtherFeaturesService;
    private final PhoneService phoneService;

    @Autowired
    public OtherFeatureController(PhoneOtherFeaturesService phoneOtherFeaturesService,
                                  PhoneService phoneService) {
        this.phoneOtherFeaturesService = phoneOtherFeaturesService;
        this.phoneService = phoneService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCharacteristics(@PathVariable("product_id") Long id) {

        //List<OtherFeatures> characteristicList = phoneService.getProductById(id).getCharacteristicList();

       // return new ResponseEntity<>(characteristicList, HttpStatus.OK);
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCharacteristic(@PathVariable("product_id") Long id,
                                                  @RequestBody ProductCharacteristicDto characteristicDto) {

        phoneOtherFeaturesService.addNewCharacteristic(convertToProductCharacteristic(characteristicDto), id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{characteristic_id}")
    public ResponseEntity<?> updateCharacteristic(@RequestBody ProductCharacteristicDto editedCharacteristicDto,
                                                  @PathVariable("characteristic_id") Long id) {
        OtherFeatures updatedCharacteristic =
                phoneOtherFeaturesService.updateCharacteristic(convertToProductCharacteristic(editedCharacteristicDto), id);

        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }

    @DeleteMapping("/{characteristic_id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("characteristic_id") Long id) {

        phoneOtherFeaturesService.deleteCharacteristic(id);

        return new ResponseEntity<>("Characteristic deleted successfully.", HttpStatus.OK);
    }

    private OtherFeatures convertToProductCharacteristic(ProductCharacteristicDto characteristicDto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(characteristicDto, OtherFeatures.class);
    }
}
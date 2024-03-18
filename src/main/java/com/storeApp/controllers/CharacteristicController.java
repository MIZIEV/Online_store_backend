package com.storeApp.controllers;

import com.storeApp.models.ProductCharacteristic;
import com.storeApp.service.ProductCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/product/characteristic")
public class CharacteristicController {

    private final ProductCharacteristicService characteristicService;

    @Autowired
    public CharacteristicController(ProductCharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @GetMapping("/list")
    public List<ProductCharacteristic> getAllCharacteristics() {
        return characteristicService.getAllCharacteristics();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCharacteristic(@RequestBody ProductCharacteristic characteristic) {

        ProductCharacteristic newCharacteristic = characteristicService.addNewCharacteristic(characteristic);

        return new ResponseEntity<>(newCharacteristic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCharacteristic(@RequestBody ProductCharacteristic editedCharacteristic,
                                                  @PathVariable("id") Long id) {
        ProductCharacteristic updatedCharacteristic = characteristicService.updateCharacteristic(editedCharacteristic, id);

        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("id") Long id){

        characteristicService.deleteCharacteristic(id);

        return new ResponseEntity<>("Characteristic deleted successfully.",HttpStatus.OK);
    }
}
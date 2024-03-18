package com.storeApp.controllers;

import com.storeApp.models.ProductCharacteristic;
import com.storeApp.service.ProductCharacteristicService;
import com.storeApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/product/{product_id}/characteristic")
public class CharacteristicController {

    private final ProductCharacteristicService characteristicService;
    private final ProductService productService;

    @Autowired
    public CharacteristicController(ProductCharacteristicService characteristicService, ProductService productService) {
        this.characteristicService = characteristicService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCharacteristics(@PathVariable("product_id") Long id) {

        List<ProductCharacteristic> characteristicList = productService.getProductById(id).getCharacteristicList();

        return new ResponseEntity<>(characteristicList,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCharacteristic(@PathVariable("product_id") Long id,
                                                  @RequestBody ProductCharacteristic characteristic) {

        characteristicService.addNewCharacteristic(characteristic,id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{characteristic_id}")
    public ResponseEntity<?> updateCharacteristic(@RequestBody ProductCharacteristic editedCharacteristic,
                                                  @PathVariable("characteristic_id") Long id) {
        ProductCharacteristic updatedCharacteristic = characteristicService.updateCharacteristic(editedCharacteristic, id);

        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }

    @DeleteMapping("/{characteristic_id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable("characteristic_id") Long id){

        characteristicService.deleteCharacteristic(id);

        return new ResponseEntity<>("Characteristic deleted successfully.",HttpStatus.OK);
    }
}
package com.storeApp.controllers;

import com.storeApp.dto.ColorDto;
import com.storeApp.models.Color;
import com.storeApp.service.ColorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/color")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewColor(@Valid @RequestBody ColorDto colorDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();

            for (FieldError error : result.getFieldErrors()) {
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(colorService.addNewColor(convertToColor(colorDto)), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllColors() {
        return new ResponseEntity<>(colorService.getAllColors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneColor(@PathVariable("id") Long id) {
        return new ResponseEntity<>(colorService.getColorById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@RequestBody ColorDto editedColor, @PathVariable("id") Long id) {
        return new ResponseEntity<>(colorService.updateColor(convertToColor(editedColor), id), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable("id") Long id) {
        colorService.deleteColor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Color convertToColor(ColorDto colorDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(colorDto, Color.class);
    }
}

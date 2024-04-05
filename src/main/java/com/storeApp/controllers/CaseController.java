package com.storeApp.controllers;

import com.storeApp.dto.CaseDto;
import com.storeApp.models.Case;
import com.storeApp.models.Color;
import com.storeApp.service.CaseService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/case")
@CrossOrigin("*")
public class CaseController {

    private final CaseService caseService;

    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCase(@Valid @RequestBody CaseDto newCase, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error:\n");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";\n");
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>(caseService.addNewCase(converteToCase(newCase)), HttpStatus.CREATED);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCases() {
        return new ResponseEntity<>(caseService.getAllCases(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCaseById(@PathVariable("id") long id) {
        Case newCase = caseService.getCaseById(id);
        return new ResponseEntity<>(newCase, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> putTheMark(@PathVariable("id") Long id, @RequestBody CaseDto caseDto) {
        caseService.putTheMark(id, caseDto.getRating());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/color")
    public ResponseEntity<?> putTheColors(@PathVariable("id") Long id, @RequestBody Set<Long> colorsId) {
        return new ResponseEntity<>(caseService.putTheColors(id, colorsId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCase(@RequestBody CaseDto editedCase, @PathVariable("id") Long id) {
        Case caseForUpdating = caseService.updateCase(converteToCase(editedCase), id);
        return new ResponseEntity<>(caseForUpdating, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteCase(@PathVariable("id") long id) {
        caseService.deleteCase(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Case converteToCase(CaseDto caseDto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(caseDto, Case.class);
    }
}

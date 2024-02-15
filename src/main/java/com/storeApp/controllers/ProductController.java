package com.storeApp.controllers;

import com.storeApp.models.Product;
import com.storeApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public List<Product> getAllPhones() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getPhoneById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewPhone(@RequestBody Product product) {
        productService.addNewProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        productService.updateProduct(product, id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletePhone(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

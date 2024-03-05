package com.storeApp.controllers;

import com.storeApp.dto.ProductDto;
import com.storeApp.models.Product;
import com.storeApp.service.CategoryService;
import com.storeApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<Product> getAllProducts(@RequestParam(name = "sort", defaultValue = "asc") String sort,
                                        @RequestParam(name = "categoryid", required = false) Long categoryid,
                                        @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        if (searchTerm != null && !searchTerm.isEmpty()) {
            String[] searchTerms = searchTerm.split("\\s+");

            if (searchTerms.length == 2) {
                return productService.getProductsByBrandAndModel(searchTerms[0], searchTerms[1]);
            } else {
                List<Product> byBrandOrModel = productService.getProductsByBrandOrModel(searchTerm, searchTerm);
                List<Product> byPartialModel = productService.getProductsByModelContainingIgnoreCase(searchTerm);

                byBrandOrModel.addAll(byPartialModel);
                return byBrandOrModel;
            }
        } else {

            return productService.getAllProducts(sort, categoryid);
        }
    }

    /*
        @GetMapping("/list/search")
        public List<Product> getProductsByBrandAndModel(@RequestParam String searchTerm) {
            String[] searchTerms = searchTerm.split("\\s+");

            if (searchTerms.length == 2) {
                return productService.getProductsByBrandAndModel(searchTerms[0], searchTerms[1]);
            } else {
                List<Product> byBrandOrModel = productService.getProductsByBrandOrModel(searchTerm, searchTerm);
                List<Product> byPartialModel = productService.getProductsByModelContainingIgnoreCase(searchTerm);

                byBrandOrModel.addAll(byPartialModel);

                return byBrandOrModel;
            }
        }
    */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            String message = "Error: " + "Product with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductDto productDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error: ");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";");
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        } else {
            productService.addNewProduct(convertToProduct(productDto));
            return new ResponseEntity<>(productDto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestBody Product editedProduct, @PathVariable Long id) {

        Product product = productService.updateProduct(editedProduct, id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            String message = "Error: " + "Product with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Product convertToProduct(ProductDto productDto) {

        Product product = new Product();

        product.setBrand(productDto.getBrand());
        product.setModel(productDto.getModel());
        product.setDescription(productDto.getDescription());
        product.setPictureURL(productDto.getPictureURL());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());

        return product;
    }

}
package com.storeApp.service.implementation;

import com.storeApp.models.Category;
import com.storeApp.models.Product;
import com.storeApp.repository.CategoryRepository;
import com.storeApp.repository.ProductRepository;
import com.storeApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(String sort, Long categoryId) {

        List<Product> products = null;

        if (categoryId != null) {
            products = getAllProductsFilteredByCategory(categoryRepository.findCategoryById(categoryId).get());
        } else {
            products = productRepository.findAll();
        }

        if ("min".equalsIgnoreCase(sort)) {
            products.sort(Comparator.comparing(Product::getPrice));
        } else if ("max".equalsIgnoreCase(sort)) {
            products.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        return products;
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @Override
    public List<Product> getProductsByBrandAndModel(String brand, String model) {
        return productRepository.findByBrandAndModel(brand, model);
    }

    @Override
    public List<Product> getProductsByBrandOrModel(String brand, String model) {
        return productRepository.findByBrandOrModel(brand, model);
    }

    @Override
    public List<Product> getProductsByModelContainingIgnoreCase(String model) {
        return productRepository.findByModelContainingIgnoreCase(model);
    }

    @Override
    public List<Product> getAllProductsFilteredByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product getProductById(Long id) {

        Product product = null;

        if (productRepository.findProductById(id).isPresent()) {
            product = productRepository.findProductById(id).get();
        }
        return product;
    }

    @Override
    public Product getProductByModel(String model) {
        Optional<Product> optionalProduct = productRepository.findProductByModel(model);
        Product product = null;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw null;         //todo create exception for non existing product
        }
        return product;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProduct(Long id) {

        Product product = null;

        if (productRepository.findProductById(id).isPresent()) {
            product = productRepository.findProductById(id).get();
            productRepository.delete(product);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Product updateProduct(Product editedProduct, Long id) {

        Product productForUpdating = null;

        if (productRepository.findProductById(id).isPresent()) {
            productForUpdating = productRepository.findProductById(id).get();

            productForUpdating.setId(id);
            productForUpdating.setBrand(editedProduct.getBrand());
            productForUpdating.setModel(editedProduct.getModel());
            productForUpdating.setDescription(editedProduct.getDescription());
            productForUpdating.setPrice(editedProduct.getPrice());
            productForUpdating.setPictureURL(editedProduct.getPictureURL());

            productRepository.save(productForUpdating);
            return productForUpdating;
        }
        return null;
    }
}
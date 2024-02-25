package com.storeApp.service.implementation;

import com.storeApp.models.Product;
import com.storeApp.repository.ProductRepository;
import com.storeApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public List<Product> getAllProductOrderedByPrice() { return productRepository.findAllOrderedByPrice(); }
    @Override
    public List<Product> getAllProductOrderedByPriceDesc() { return productRepository.findAllOrderByPriceDesc(); }

    @Override
    public Product getProductById(Long id) {

        Product product = null;

        if (productRepository.findProductById(id).isPresent()) {
            product = productRepository.findProductById(id).get();
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
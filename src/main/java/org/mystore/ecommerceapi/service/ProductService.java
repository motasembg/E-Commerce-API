package org.mystore.ecommerceapi.service;
import lombok.RequiredArgsConstructor;
import org.mystore.ecommerceapi.DatabaseTables.Category;
import org.mystore.ecommerceapi.DatabaseTables.Products;
import org.mystore.ecommerceapi.Repositories.categoryRepository;
import org.mystore.ecommerceapi.Repositories.productsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    final private productsRepository productsRepository;
    final private categoryRepository categoryRepository;

    public Products addProduct(Products products, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        products.setCategory(category);
        return productsRepository.save(products);
    }
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
    public Products getProductById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public List<Products> getProductsByCategory(Long categoryId) {
        return productsRepository.findByCategoryId(categoryId);
    }
    public Products updateProduct(Long id , Products updatedProduct ) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        products.setName(updatedProduct.getName());
        products.setDescription(updatedProduct.getDescription());
        products.setPrice(updatedProduct.getPrice());
        products.setStock(updatedProduct.getStock());
        return productsRepository.save(products);
    }
    public void deleteProduct(Long id) {
        if(!productsRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productsRepository.deleteById(id);
    }
}

package org.mystore.ecommerceapi.Controllers;
import lombok.RequiredArgsConstructor;
import org.mystore.ecommerceapi.DatabaseTables.Products;
import org.mystore.ecommerceapi.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    final ProductService productService;

    @PostMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')") // Adimns add products
    public Products addProduct(@RequestBody Products product, @PathVariable Long categoryId) {
        return productService.addProduct(product, categoryId);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Products> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Products updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}

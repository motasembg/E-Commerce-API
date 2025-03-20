package org.mystore.ecommerceapi.service;

import lombok.RequiredArgsConstructor;
import org.mystore.ecommerceapi.DatabaseTables.Category;
import org.mystore.ecommerceapi.Repositories.categoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final categoryRepository categoryRepository;
    private final ProductService productService;

    public Category createCategory(Category category) {
        categoryRepository.findByName(category.getName()).ifPresent(c -> {
            throw new RuntimeException("Category already exists!");
        });
        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

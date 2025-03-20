package org.mystore.ecommerceapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mystore.ecommerceapi.DatabaseTables.Products;
import java.util.List;

public interface productsRepository extends JpaRepository<Products, Long> {
    // Get products by category!!
    List<Products> findByCategoryId(Long categoryId);
}

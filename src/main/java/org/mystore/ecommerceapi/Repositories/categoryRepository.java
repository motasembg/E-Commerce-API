package org.mystore.ecommerceapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.mystore.ecommerceapi.DatabaseTables.Category;

import java.util.Optional;

public interface categoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

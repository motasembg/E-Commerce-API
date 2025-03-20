package org.mystore.ecommerceapi.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.mystore.ecommerceapi.DatabaseTables.CartItem;
import java.util.List;
public interface cartItemRepository extends JpaRepository<CartItem, Long> {
    // Get all cart items for a user!::
    List<CartItem> findCartItemsByUserId(Long userId);
}

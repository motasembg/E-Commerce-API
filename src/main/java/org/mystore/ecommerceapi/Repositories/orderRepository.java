package org.mystore.ecommerceapi.Repositories;
import org.mystore.ecommerceapi.DatabaseTables.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.mystore.ecommerceapi.DatabaseTables.Order;
import java.util.List;
public interface orderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(CartItem userId);
}

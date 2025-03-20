package org.mystore.ecommerceapi.Repositories;

import org.mystore.ecommerceapi.DatabaseTables.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface orderItemRepository extends JpaRepository<OrderItem, Long> {
}

package org.mystore.ecommerceapi.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.mystore.ecommerceapi.DatabaseTables.Users;
import java.util.Optional;
public interface usersRepository extends JpaRepository<Users, Long> {
    // Find user by email for authentication!!
    Optional<Users> findByEmail(String email);
}

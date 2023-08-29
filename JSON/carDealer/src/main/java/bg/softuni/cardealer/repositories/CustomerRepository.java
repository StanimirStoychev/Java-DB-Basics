package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM `customers` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Customer getRandomEntity();
}

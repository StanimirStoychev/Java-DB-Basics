package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM `customers` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Customer getRandomEntity();

    @Query(value = "SELECT * FROM `customers` " +
            "ORDER BY `date_of_birth`, " +
            "`is_young_driver`", nativeQuery = true)
    Optional<List<Customer>> getOrderedCustomers();
}

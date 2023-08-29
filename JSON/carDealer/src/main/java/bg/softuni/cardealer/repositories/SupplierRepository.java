package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT * FROM `suppliers` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Supplier getRandomEntity();
}

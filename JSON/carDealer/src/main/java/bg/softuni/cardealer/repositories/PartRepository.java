package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query(value = "SELECT * FROM `parts` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Part getRandomEntity();
}

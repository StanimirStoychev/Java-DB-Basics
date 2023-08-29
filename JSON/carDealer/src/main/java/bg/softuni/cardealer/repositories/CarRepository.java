package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM `cars` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Car getRandomEntity();
}

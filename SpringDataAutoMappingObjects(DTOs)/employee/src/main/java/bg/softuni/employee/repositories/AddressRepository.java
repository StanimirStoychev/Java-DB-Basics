package bg.softuni.employee.repositories;

import bg.softuni.employee.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCity(String city);

    Optional<Address> findById(Long id);
}

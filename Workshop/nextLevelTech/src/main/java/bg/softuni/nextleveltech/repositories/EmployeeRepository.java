package bg.softuni.nextleveltech.repositories;

import bg.softuni.nextleveltech.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<List<Employee>> findAllByAgeGreaterThan(byte age);
}

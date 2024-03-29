package bg.softuni.cardealer.repositories;

import bg.softuni.cardealer.domain.dtos.supplier.SupplierExportDTO;
import bg.softuni.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT * FROM `suppliers` ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Supplier getRandomEntity();

    Optional<List<Supplier>> findAllByIsImporterFalse();

    @Query("SELECT new bg.softuni.cardealer.domain.dtos.supplier.SupplierExportDTO(s.id, s.name, count(p.id)) FROM Supplier s " +
            "INNER JOIN Part p ON p.supplier.id = s.id " +
            "WHERE s.isImporter = FALSE")
    Optional<List<SupplierExportDTO>> findAllWhoDontImportParts();
}

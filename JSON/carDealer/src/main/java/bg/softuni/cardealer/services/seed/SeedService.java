package bg.softuni.cardealer.services.seed;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedSuppliers() throws FileNotFoundException;

    void seedParts() throws FileNotFoundException;

    void seedCars() throws FileNotFoundException;

    void seedCustomers() throws FileNotFoundException;

    void seedSales();

    default void seedAll() throws FileNotFoundException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }
}

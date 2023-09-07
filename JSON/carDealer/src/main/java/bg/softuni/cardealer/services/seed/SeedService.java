package bg.softuni.cardealer.services.seed;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedSuppliers() throws FileNotFoundException, JAXBException;

    void seedParts() throws FileNotFoundException;

    void seedCars() throws FileNotFoundException;

    void seedCustomers() throws FileNotFoundException;

    void seedSales();

    default void seedAll() throws FileNotFoundException, JAXBException {
        seedSuppliers();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        seedSales();
    }
}

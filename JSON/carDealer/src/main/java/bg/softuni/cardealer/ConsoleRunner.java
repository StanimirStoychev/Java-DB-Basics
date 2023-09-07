package bg.softuni.cardealer;

import bg.softuni.cardealer.services.car.CarService;
import bg.softuni.cardealer.services.customer.CustomerService;
import bg.softuni.cardealer.services.seed.SeedService;
import bg.softuni.cardealer.services.supplier.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    @Autowired
    public ConsoleRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        this.seedService.seedAll();
//        this.customerService.getOrderedCustomers();
//        this.carService.getAllToyotaCarsOrdered();
//        this.supplierService.getAllSuppliersWhoNotImport();
//        this.carService.getAllCarsWithParts();


    }
}

package bg.softuni.cardealer;

import bg.softuni.cardealer.services.seed.SeedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    @Autowired
    public ConsoleRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        this.seedService.seedSuppliers();
        this.seedService.seedParts();
        this.seedService.seedCars();
        this.seedService.seedCustomers();
        this.seedService.seedSales();
//        this.seedService.seedAll();
    }
}

package bg.softuni.cardealer;

import bg.softuni.cardealer.services.customer.CustomerService;
import bg.softuni.cardealer.services.seed.SeedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;

    @Autowired
    public ConsoleRunner(SeedService seedService, CustomerService customerService) {
        this.seedService = seedService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

//        this.seedService.seedAll();
        this.customerService.getOrderedCustomers();

    }
}

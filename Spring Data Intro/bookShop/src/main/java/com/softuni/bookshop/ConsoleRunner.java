package com.softuni.bookshop;

import com.softuni.bookshop.services.seed.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    public ConsoleRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedAll();
    }
}

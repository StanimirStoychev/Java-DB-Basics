package com.example.productsshop;

import com.example.productsshop.services.UserService;
import com.example.productsshop.services.product.ProductService;
import com.example.productsshop.services.seed.SeedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedService.seedAll();

        this.productService.findAllByPriceBetweenAndBuyerIsNullOrderByPrice();

        this.userService.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName();
    }
}

package com.example.productsshop;

import com.example.productsshop.services.category.CategoryService;
import com.example.productsshop.services.user.UserService;
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

    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedService.seedAll();

//        this.productService.findAllByPriceBetweenAndBuyerIsNullOrderByPrice();

//        this.userService.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName();

//        this.categoryService.getCategorySummary();

        this.userService.usersAndProducts();
    }
}

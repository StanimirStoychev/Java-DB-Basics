package com.example.productsshop.services;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws FileNotFoundException;

    void seedProducts();

    void seedCategories();

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedProducts();
        seedCategories();
    }
}

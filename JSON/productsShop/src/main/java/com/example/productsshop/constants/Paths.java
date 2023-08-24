package com.example.productsshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_PATH = Path.of("src/main/resources/files/users.json");
    public static final Path CATEGORIES_PATH = Path.of("src/main/resources/files/categories.json");
    public static final Path PRODUCTS_PATH = Path.of("src/main/resources/files/products.json");

    public static final Path PRODUCTS_IN_RANGE_OUTPUT_PATH = Path.of("src/main/resources/output/products-in-range.json");
    public static final Path USER_SOLD_PRODUCTS_OUTPUT_PATH = Path.of("src/main/resources/output/users-sold-products.json");
}
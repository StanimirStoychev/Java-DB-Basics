package com.example.productsshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_JSON_PATH = Path.of("src/main/resources/files/json/users.json");
    public static final Path CATEGORIES_JSON_PATH = Path.of("src/main/resources/files/json/categories.json");
    public static final Path PRODUCTS_JSON_PATH = Path.of("src/main/resources/files/json/products.json");

    public static final Path USERS_XML_PATH = Path.of("src/main/resources/files/xml/users.xml");
    public static final Path CATEGORIES_XML_PATH = Path.of("src/main/resources/files/xml/categories.xml");
    public static final Path PRODUCTS_XML_PATH = Path.of("src/main/resources/files/xml/products.xml");

    public static final Path PRODUCTS_IN_RANGE_OUTPUT_PATH =
            Path.of("src/main/resources/output/json/products-in-range.json");
    public static final Path USER_SOLD_PRODUCTS_OUTPUT_PATH =
            Path.of("src/main/resources/output/json/users-sold-products.json");
    public static final Path CATEGORIES_BY_PRODUCTS_OUTPUT_PATH =
            Path.of("src/main/resources/output/json/categories-by-products.json");
    public static final Path USERS_WITH_PRODUCTS_OUTPUT_PATH =
            Path.of("src/main/resources/output/json/users-and-products.json");

    public static final Path PRODUCTS_IN_RANGE_XML_OUTPUT_PATH =
            Path.of("src/main/resources/output/xml/products-in-range.xml");
    public static final Path USER_SOLD_PRODUCTS_XML_OUTPUT_PATH =
            Path.of("src/main/resources/output/xml/users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_OUTPUT_PATH =
            Path.of("src/main/resources/output/xml/categories-by-products.xml");
    public static final Path USERS_WITH_PRODUCTS_XML_OUTPUT_PATH =
            Path.of("src/main/resources/output/xml/users-and-products.xml");
}
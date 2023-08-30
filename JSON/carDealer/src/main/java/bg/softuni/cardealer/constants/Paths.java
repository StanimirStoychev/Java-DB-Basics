package bg.softuni.cardealer.constants;

import java.nio.file.Path;

public enum Paths {
    ;

    public static final Path SUPPLIERS_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "suppliers.json");
    public static final Path PARTS_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "parts.json");
    public static final Path CAR_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "cars.json");
    public static final Path CUSTOMER_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "customers.json");

    public static final Path ORDERED_CUSTOMERS_OUTPUT =
            Path.of("src", "main", "resources", "output", "ordered-customers.json");
    public static final Path TOYOTA_CARS_OUTPUT =
            Path.of("src", "main", "resources", "output", "toyota-cars.json");
    public static final Path LOCAL_SUPPLIERS_OUTPUT =
            Path.of("src", "main", "resources", "output", "local-suppliers.json");
    public static final Path CARS_AND_PARTS_OUTPUT =
            Path.of("src", "main", "resources", "output", "cars-and-parts.json");
}

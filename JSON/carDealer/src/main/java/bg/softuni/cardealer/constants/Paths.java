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
}

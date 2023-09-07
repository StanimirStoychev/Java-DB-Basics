package bg.softuni.cardealer.constants;

import java.nio.file.Path;

public enum Paths {
    ;

    //Inputs

    //JSON
    public static final Path SUPPLIERS_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "json", "suppliers.json");
    public static final Path PARTS_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "json", "parts.json");
    public static final Path CAR_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "json", "cars.json");
    public static final Path CUSTOMER_INPUT_PATH =
            Path.of("src", "main", "resources", "files", "json", "customers.json");

    //XML
    public static final Path SUPPLIERS_INPUT_XML_PATH =
            Path.of("src", "main", "resources", "files", "xml", "suppliers.xml");
    public static final Path PARTS_INPUT_XML_PATH =
            Path.of("src", "main", "resources", "files", "xml", "parts.xml");
    public static final Path CAR_INPUT_XML_PATH =
            Path.of("src", "main", "resources", "files", "xml", "cars.xml");
    public static final Path CUSTOMER_INPUT_XML_PATH =
            Path.of("src", "main", "resources", "files", "xml", "customers.xml");

    //Outputs

    //JSON
    public static final Path ORDERED_CUSTOMERS_OUTPUT =
            Path.of("src", "main", "resources", "output", "json", "ordered-customers.json");
    public static final Path TOYOTA_CARS_OUTPUT =
            Path.of("src", "main", "resources", "output", "json", "toyota-cars.json");
    public static final Path LOCAL_SUPPLIERS_OUTPUT =
            Path.of("src", "main", "resources", "output", "json", "local-suppliers.json");
    public static final Path CARS_AND_PARTS_OUTPUT =
            Path.of("src", "main", "resources", "output", "json", "cars-and-parts.json");

    //XML
    public static final Path ORDERED_CUSTOMERS_XML_OUTPUT =
            Path.of("src", "main", "resources", "output", "xml", "ordered-customers.xml");
    public static final Path TOYOTA_CARS_XML_OUTPUT =
            Path.of("src", "main", "resources", "output", "xml", "toyota-cars.xml");
    public static final Path LOCAL_SUPPLIERS_XML_OUTPUT =
            Path.of("src", "main", "resources", "output", "xml", "local-suppliers.xml");
    public static final Path CARS_AND_PARTS_XML_OUTPUT =
            Path.of("src", "main", "resources", "output", "xml", "cars-and-parts.xml");
}

package com.example.productsshop.services.seed;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws FileNotFoundException, JAXBException;

    void seedProducts() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException, JAXBException;

    default void seedAll() throws FileNotFoundException, JAXBException {
        seedUsers();
        seedCategories();
//        seedProducts();
    }
}

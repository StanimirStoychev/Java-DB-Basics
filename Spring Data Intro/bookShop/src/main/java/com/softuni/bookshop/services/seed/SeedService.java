package com.softuni.bookshop.services.seed;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategories() throws IOException;

    default void seedAll() throws IOException{
        seedAuthors();
        seedCategories();
        seedBooks();
    }
}

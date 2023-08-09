package com.softuni.bookshop.services.author;

import com.softuni.bookshop.domain.entities.Author;

import java.util.List;

public interface AuthorService {

    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();

    Author getRandomAuthor();
}

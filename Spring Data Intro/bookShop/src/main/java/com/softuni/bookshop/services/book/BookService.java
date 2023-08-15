package com.softuni.bookshop.services.book;

import com.softuni.bookshop.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> getAllBooksAfterYear(LocalDate date);

    List<Book> getAllBooksWithAuthorFullNameOrdered(String firstName, String lastName);
}

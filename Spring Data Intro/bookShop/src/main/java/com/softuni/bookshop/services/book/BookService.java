package com.softuni.bookshop.services.book;

import com.softuni.bookshop.domain.entities.Book;

import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();
}

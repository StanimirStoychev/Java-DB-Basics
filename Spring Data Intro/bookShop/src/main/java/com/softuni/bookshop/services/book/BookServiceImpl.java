package com.softuni.bookshop.services.book;

import com.softuni.bookshop.domain.entities.Book;
import com.softuni.bookshop.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public List<Book> getAllBooksAfterYear(LocalDate date) {
        List<Book> allByReleaseDateAfter = this.bookRepository.findAllByReleaseDateAfter(date).get();

        return allByReleaseDateAfter;
    }

    @Override
    public List<Book> getAllBooksWithAuthorFullNameOrdered(String firstName, String lastName) {
        List<Book> orderedBooks = this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(firstName, lastName).get();

        return orderedBooks;
    }


}

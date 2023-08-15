package com.softuni.bookshop;

import com.softuni.bookshop.domain.entities.Author;
import com.softuni.bookshop.domain.entities.Book;
import com.softuni.bookshop.services.author.AuthorService;
import com.softuni.bookshop.services.book.BookService;
import com.softuni.bookshop.services.seed.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedAll();

    //    printAllBooksAfterYear();
    //    printAllAuthorsWithBooksBeforeYear();
    //    printAllBooksWithAuthorFullNameAndOrdered();
    }

    private void printAllBooksAfterYear() {
        this.bookService.getAllBooksAfterYear(LocalDate.of(1999, 12, 31))
                .forEach(Book::printTitle);
    }

    private void printAllAuthorsWithBooksBeforeYear() {
        this.authorService.getAllByBooksBeforeYear(LocalDate.of(1990, 1, 1))
                .forEach(Author::printFullName);
    }

    private void printAllBooksWithAuthorFullNameAndOrdered() {
        this.bookService.getAllBooksWithAuthorFullNameOrdered("George", "Powell")
                .forEach(Book::printTitleReleaseDateAndCopies);
    }
}

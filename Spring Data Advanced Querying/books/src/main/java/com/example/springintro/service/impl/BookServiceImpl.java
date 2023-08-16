package com.example.springintro.service.impl;

import com.example.springintro.model.dto.BookInfo;
import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal less, BigDecimal greater) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(less, greater);
    }

    @Override
    public List<Book> findAllByReleaseDateNot(LocalDate localDate) {
        return this.bookRepository.findAllByReleaseDateNot(localDate);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate localDate) {
        return this.bookRepository.findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<Book> findAllByTitleContainingIgnoreCase(String contains) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(contains);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String lastName) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(lastName);
    }

    @Override
    public int countOfBooksWithLongerSizeThanGivenNumber(int number) {
        return this.bookRepository.countOfBooksWithLongerSizeThanGivenNumber(number);
    }

    @Override
    public BookInfo findFirstByByTitle(String title) {
        final BookInfo bookInfo = this.bookRepository.findFirstByByTitle(title).orElseThrow(NoSuchElementException::new);

        System.out.println(bookInfo.toString());
        return bookInfo;
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}

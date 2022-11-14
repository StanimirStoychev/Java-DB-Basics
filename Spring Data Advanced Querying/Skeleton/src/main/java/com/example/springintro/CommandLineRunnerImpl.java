package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final Scanner scanner;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        final String title = scanner.nextLine();

        this.bookService.findFirstByByTitle(title);
    }

    public void bookTitlesByAgeRestriction(){
        final String ageRestrictionType = scanner.nextLine();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionType.toUpperCase());

        this.bookService.findAllByAgeRestriction(ageRestriction).stream()
                .map(Book::getTitle).forEach(System.out::println);
    }

    public void goldenBooks() {
        EditionType editionType = EditionType.GOLD;
        int copies = 5000;

        this.bookService.findAllByEditionTypeAndCopiesLessThan(editionType, copies).stream()
                .map(Book::getTitle).forEach(System.out::println);
    }

    public void booksByPrice() {
        BigDecimal less = BigDecimal.valueOf(5);
        BigDecimal greater = BigDecimal.valueOf(40);
        this.bookService.findAllByPriceLessThanOrPriceGreaterThan(less, greater).stream()
                .map(Book::bookTitleAndPriceFormat)
                .forEach(System.out::println);
    }

    public void notReleasedBooks() {
        this.bookService.findAllByReleaseDateNot(LocalDate.of(2000,1,1))
                .stream().map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void booksReleasedBefore() {
        String[] date = scanner.nextLine().split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        this.bookService.findAllByReleaseDateBefore(LocalDate.of(year,month,day))
                .stream().map(Book::bookTitleEditionTypeAndPriceFormat)
                .forEach(System.out::println);
    }

    public void authorsSearch() {
        String ending = scanner.nextLine();
        this.authorService.findAllByFirstNameEndingWith(ending)
                .stream().map(Author::FullNameFormat)
                .forEach(System.out::println);
    }

    public void booksSearch() {
        String contains = scanner.nextLine();
        this.bookService.findAllByTitleContainingIgnoreCase(contains)
                .stream().map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void bookTitlesSearch() {
        String lastNameStartsWith = scanner.nextLine();

        this.bookService.findAllByAuthorLastNameStartingWith(lastNameStartsWith)
                .stream().map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void countBooks() {
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println(this.bookService.countOfBooksWithLongerSizeThanGivenNumber(number));
    }

    //public void totalBookCopies() {
    //    this.authorService.totalBookCopiesByAuthor().stream().map(Author::FullNameFormat)
    //            .forEach(System.out::println);
    //}
}
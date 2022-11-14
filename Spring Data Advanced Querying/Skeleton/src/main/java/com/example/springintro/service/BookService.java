package com.example.springintro.service;

import com.example.springintro.model.dto.BookInfo;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal less, BigDecimal greater);

    List<Book> findAllByReleaseDateNot(LocalDate localDate);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContainingIgnoreCase(String contains);

    List<Book> findAllByAuthorLastNameStartingWith(String lastName);

    int countOfBooksWithLongerSizeThanGivenNumber(int number);

    BookInfo findFirstByByTitle(String title);
}

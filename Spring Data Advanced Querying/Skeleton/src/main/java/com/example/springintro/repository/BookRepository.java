package com.example.springintro.repository;

import com.example.springintro.model.dto.BookInfo;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal less, BigDecimal greater);

    List<Book> findAllByReleaseDateNot(LocalDate localDate);

    List<Book> findAllByTitleContainingIgnoreCase(String contains);

    List<Book> findAllByAuthorLastNameStartingWith(String lastName);

    @Query("select count(b.id) from Book b where length(b.title) > :number")
    int countOfBooksWithLongerSizeThanGivenNumber(int number);

    @Query("select new com.example.springintro.model.dto.BookInfo(b.title, b.editionType, b.ageRestriction, b.price)" +
            " from Book b where b.title = :title")
    Optional<BookInfo> findFirstByByTitle(String title);
}

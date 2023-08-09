package com.softuni.bookshop.services.seed;

import com.softuni.bookshop.domain.entities.Author;
import com.softuni.bookshop.domain.entities.Book;
import com.softuni.bookshop.domain.entities.Category;
import com.softuni.bookshop.domain.enums.AgeRestriction;
import com.softuni.bookshop.domain.enums.EditionType;
import com.softuni.bookshop.services.author.AuthorService;
import com.softuni.bookshop.services.book.BookService;
import com.softuni.bookshop.services.category.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.bookshop.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!authorService.isDataSeeded()) {
            this.authorService.seedAuthors(Files.readAllLines(Path.of(RESOURCE_URL + AUTHORS_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(s -> Author.builder()
                            .firstName(s.split(" ")[0])
                            .lastName(s.split(" ")[1])
                            .build())
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookService.isDataSeeded()) return;

        final List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(row -> {
                    String[] args = row.split("\\s+");
                    String title = Arrays.stream(args)
                            .skip(5)
                            .collect(Collectors.joining(" "));

                    return Book.builder()
                            .author(this.authorService.getRandomAuthor())
                            .categories(this.categoryService.getRandomCategories())
                            .title(title)
                            .editionType(EditionType.values()[Integer.parseInt(args[0])])
                            .ageRestriction(AgeRestriction.values()[Integer.parseInt(args[4])])
                            .releaseDate(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                            .copies(Integer.parseInt(args[2]))
                            .price(new BigDecimal(args[3]))
                            .build();
                }).toList();

        this.bookService.seedBooks(books);
    }

    @Override
    public void seedCategories() throws IOException {
        if (!this.categoryService.isDataSeeded()) {
            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCE_URL + CATEGORIES_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> Category.builder()
                            .name(name)
                            .build())
                    .toList());
        }
    }
}

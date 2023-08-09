package com.softuni.bookshop.services.category;

import com.softuni.bookshop.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<Category> categories);

    boolean isDataSeeded();

    Category getRandomCategory();

    Set<Category> getRandomCategories();
}

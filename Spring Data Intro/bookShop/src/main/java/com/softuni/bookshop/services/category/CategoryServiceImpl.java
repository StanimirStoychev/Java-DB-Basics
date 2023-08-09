package com.softuni.bookshop.services.category;

import com.softuni.bookshop.domain.entities.Category;
import com.softuni.bookshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public Category getRandomCategory() {
        final long count = this.categoryRepository.count();

        if (count != 0) {
            long randomId = new Random().nextLong(1L, count) + 1;
            return this.categoryRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < 1; i++) {
            categories.add(getRandomCategory());
        }

        return categories;
    }
}

package com.example.productsshop.services.category;

import com.example.productsshop.domain.dtos.category.CategoryProductSummaryDTO;
import com.example.productsshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productsshop.constants.Paths.CATEGORIES_BY_PRODUCTS_OUTPUT_PATH;
import static com.example.productsshop.constants.Utils.writeIntoJsonFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<CategoryProductSummaryDTO> getCategorySummary() throws IOException {
        List<CategoryProductSummaryDTO> categories = this.categoryRepository.getCategorySummary().orElseThrow(NoSuchElementException::new)
                .stream()
                .map(category -> modelMapper.map(category, CategoryProductSummaryDTO.class))
                .toList();

        writeIntoJsonFile(categories, CATEGORIES_BY_PRODUCTS_OUTPUT_PATH);

        return categories;
    }
}

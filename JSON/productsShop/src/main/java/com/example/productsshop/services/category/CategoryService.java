package com.example.productsshop.services.category;

import com.example.productsshop.domain.dtos.category.CategoryProductSummaryDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoryProductSummaryDTO> getCategorySummary() throws IOException;
}

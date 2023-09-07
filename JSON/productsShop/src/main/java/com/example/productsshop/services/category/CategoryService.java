package com.example.productsshop.services.category;

import com.example.productsshop.domain.dtos.category.CategoryProductSummaryDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoryProductSummaryDTO> writeCategorySummary() throws IOException, JAXBException;
}

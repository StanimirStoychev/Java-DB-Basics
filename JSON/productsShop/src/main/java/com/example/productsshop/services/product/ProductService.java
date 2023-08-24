package com.example.productsshop.services.product;

import com.example.productsshop.domain.dtos.product.ProductInRangeWithNoBuyerDTO;
import com.example.productsshop.domain.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice() throws IOException;
}

package com.example.productsshop.services.product;

import com.example.productsshop.domain.dtos.product.ProductDTO;
import com.example.productsshop.domain.dtos.product.ProductInRangeWithNoBuyerDTO;
import com.example.productsshop.domain.entities.Product;
import com.example.productsshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productsshop.constants.Paths.PRODUCTS_IN_RANGE_OUTPUT_PATH;
import static com.example.productsshop.constants.Utils.writeIntoJsonFile;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository  productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice() throws IOException {
        List<ProductInRangeWithNoBuyerDTO> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(
                        new BigDecimal(500),
                        new BigDecimal(1000))
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .map(ProductDTO::productInRangeWithNoBuyerDTO)
                .toList();

        writeIntoJsonFile(products, PRODUCTS_IN_RANGE_OUTPUT_PATH);

        return products;
    }
}

package com.example.productsshop.services.product;

import com.example.productsshop.domain.dtos.product.ProductInRangeWithNoBuyerDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductInRangeWithNoBuyerDTO> writeProductsInRange() throws IOException, JAXBException;
}

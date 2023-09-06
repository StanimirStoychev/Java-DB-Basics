package com.example.productsshop.services.product;

import com.example.productsshop.domain.dtos.product.ProductDTO;
import com.example.productsshop.domain.dtos.product.ProductExportXMLDTO;
import com.example.productsshop.domain.dtos.product.ProductInRangeWithNoBuyerDTO;
import com.example.productsshop.domain.dtos.product.wrappers.ProductsWrapperDTO;
import com.example.productsshop.domain.entities.Product;
import com.example.productsshop.domain.entities.User;
import com.example.productsshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productsshop.constants.Paths.PRODUCTS_IN_RANGE_OUTPUT_PATH;
import static com.example.productsshop.constants.Paths.PRODUCTS_IN_RANGE_XML_OUTPUT_PATH;
import static com.example.productsshop.constants.Utils.writeIntoJsonFile;
import static com.example.productsshop.constants.Utils.writeIntoXmlFile;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository  productRepository;
    private final ModelMapper modelMapper;
    private final TypeMap<Product, ProductExportXMLDTO> productToDTOMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductExportXMLDTO> typeMap =
                this.modelMapper.createTypeMap(Product.class, ProductExportXMLDTO.class);

        this.productToDTOMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                .map(Product::getSeller, ProductExportXMLDTO::setSeller));
    }

    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDTO> writeProductsInRange() throws IOException, JAXBException {
        List<ProductInRangeWithNoBuyerDTO> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(
                        new BigDecimal(500),
                        new BigDecimal(1000))
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .map(ProductDTO::productInRangeWithNoBuyerDTO)
                .toList();

        writeIntoJsonFile(products, PRODUCTS_IN_RANGE_OUTPUT_PATH);

        List<Product> products1 = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(
                new BigDecimal(500),
                new BigDecimal(1000)).get();

        List<ProductExportXMLDTO> dtos = products1
                .stream()
                .map(this.productToDTOMapping::map)
                .toList();

        ProductsWrapperDTO productsWrapperDTO = new ProductsWrapperDTO(dtos);

        writeIntoXmlFile(productsWrapperDTO, PRODUCTS_IN_RANGE_XML_OUTPUT_PATH);



        return products;
    }
}

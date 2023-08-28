package com.example.productsshop.domain.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsSoldWithCountDTO {

    private Integer count;

    private List<ProductBasicInfo> products;

    public ProductsSoldWithCountDTO(List<ProductBasicInfo> products) {
        this.products = products;
        this.count = products.size();
    }
}

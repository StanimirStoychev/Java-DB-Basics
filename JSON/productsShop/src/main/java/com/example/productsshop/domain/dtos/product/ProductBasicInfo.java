package com.example.productsshop.domain.dtos.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBasicInfo {

    private String name;

    private BigDecimal price;
}

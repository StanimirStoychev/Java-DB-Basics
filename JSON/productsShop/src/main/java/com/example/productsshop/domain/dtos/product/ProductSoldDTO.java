package com.example.productsshop.domain.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSoldDTO {

    private String name;

    private BigDecimal price;

    private String buyerFirstName;

    private String buyerLastName;
}

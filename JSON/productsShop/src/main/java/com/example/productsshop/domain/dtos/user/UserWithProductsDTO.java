package com.example.productsshop.domain.dtos.user;

import com.example.productsshop.domain.dtos.product.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private ProductsSoldWithCountDTO products;
}

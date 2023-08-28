package com.example.productsshop.domain.dtos.user;

import com.example.productsshop.domain.dtos.product.ProductBasicInfo;
import com.example.productsshop.domain.dtos.product.ProductDTO;
import com.example.productsshop.domain.dtos.product.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDTO> sellProducts;

    private Set<ProductDTO> boughtProducts;

    private Set<UserDTO> friends;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public ProductBasicInfo toProductBasicInfo(ProductDTO productDTO) {
        return new ProductBasicInfo(productDTO.getName(), productDTO.getPrice());
    }

    public ProductsSoldWithCountDTO toProductsSoldWithCountDTO() {
        return new ProductsSoldWithCountDTO(sellProducts
                .stream()
                .map(this::toProductBasicInfo)
                .collect(Collectors.toList()));
    }

    public UserWithProductsDTO toUserWithProductsDTO() {
        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO());
    }


}

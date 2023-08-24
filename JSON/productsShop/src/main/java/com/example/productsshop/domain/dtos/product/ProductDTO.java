package com.example.productsshop.domain.dtos.product;

import com.example.productsshop.domain.dtos.category.CategoryDTO;
import com.example.productsshop.domain.dtos.user.UserDTO;
import com.example.productsshop.domain.entities.Category;
import com.example.productsshop.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;

    private BigDecimal price;

    private UserDTO buyer;

    private UserDTO seller;

    private Set<CategoryDTO> categories;

    public ProductInRangeWithNoBuyerDTO productInRangeWithNoBuyerDTO() {
        return new ProductInRangeWithNoBuyerDTO(name, price, seller.getFullName());
    }
}

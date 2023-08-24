package com.example.productsshop.domain.dtos.user;

import com.example.productsshop.domain.entities.Product;
import com.example.productsshop.domain.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<Product> sellProducts;

    private Set<Product> boughtProducts;

    private Set<User> friends;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

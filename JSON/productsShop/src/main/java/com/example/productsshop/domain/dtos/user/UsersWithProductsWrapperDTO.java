package com.example.productsshop.domain.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersWithProductsWrapperDTO {

    private Integer usersCount;

    private List<UserWithProductsDTO> users;

    public UsersWithProductsWrapperDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}

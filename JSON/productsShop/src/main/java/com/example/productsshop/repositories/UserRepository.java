package com.example.productsshop.repositories;

import com.example.productsshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from `users` order by RAND() LIMIT 1", nativeQuery = true)
    Optional<User> getRandomEntity();

    Optional<List<User>> findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName();
}

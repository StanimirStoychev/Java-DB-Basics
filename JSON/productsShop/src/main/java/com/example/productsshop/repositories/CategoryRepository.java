package com.example.productsshop.repositories;

import com.example.productsshop.domain.dtos.category.CategoryProductSummaryDTO;
import com.example.productsshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from `categories` order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query("select new com.example.productsshop.domain.dtos.category.CategoryProductSummaryDTO" +
            "(c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "from Product as p " +
            "join p.categories as c " +
            "group by c.id " +
            "order by count(p.id)")
    Optional<List<CategoryProductSummaryDTO>> getCategorySummary();
}

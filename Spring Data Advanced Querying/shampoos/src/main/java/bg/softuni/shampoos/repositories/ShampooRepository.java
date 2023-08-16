package bg.softuni.shampoos.repositories;

import bg.softuni.shampoos.entities.Shampoo;
import bg.softuni.shampoos.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, long label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")
    List<Shampoo> findAllByIngredientsIncluded(String... ingredients);

    //@Query("SELECT s FROM Shampoo AS s " +
    //        "WHERE s.ingredients.size < :count")
    //List<Shampoo> findAllByIngredientCountLessThan(int count);
}

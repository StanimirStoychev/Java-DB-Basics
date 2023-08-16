package bg.softuni.shampoos.repositories;

import bg.softuni.shampoos.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> names);

    void deleteIngredientsByName(String name);

    //@Query("UPDATE Ingredient AS i " +
    //        "SET i.price = i.price * 1.10")
    //@Modifying
    //void updateIngredientsByPrice();

    //@Query("UPDATE Ingredient AS i " +
    //        "SET i.price = i.price * 1.1 " +
    //        "WHERE i.name = :name")
    //@Modifying
    //void upgradeIngredientsByName(String name);
}

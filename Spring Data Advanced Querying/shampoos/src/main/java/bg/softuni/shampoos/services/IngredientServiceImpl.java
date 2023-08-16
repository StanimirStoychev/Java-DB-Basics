package bg.softuni.shampoos.services;

import bg.softuni.shampoos.entities.Ingredient;
import bg.softuni.shampoos.repositories.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameStartingWith(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameInOrderByPrice(List<String> names) {
        return this.ingredientRepository.findAllByNameInOrderByPrice(names);
    }

    @Override
    @Transactional
    public void deleteIngredientsByName(String name) {
        this.ingredientRepository.deleteIngredientsByName(name);
    }

    //@Override
    //@Transactional
    //public void upgradeIngredientsByName(String name) {
    //    this.ingredientRepository.upgradeIngredientsByName(name);
    //}

    //@Override
    //@Transactional
    //public void updateIngredientsByPrice() {
    //    this.ingredientRepository.updateIngredientsByPrice();
    //}
}

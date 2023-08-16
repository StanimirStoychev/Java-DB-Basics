package bg.softuni.shampoos.services;

import bg.softuni.shampoos.entities.Shampoo;
import bg.softuni.shampoos.entities.Size;
import bg.softuni.shampoos.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findAllBySize(String size) {
        Size parsed = Size.valueOf(size.toUpperCase());

        return this.shampooRepository.findAllBySizeOrderById(parsed);
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(String size, long label) {
        Size parsed = Size.valueOf(size.toUpperCase());

        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(parsed, label);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(String price) {
        BigDecimal parsed = new BigDecimal(price);

        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(parsed);
    }

    @Override
    public int countAllByPriceLessThan(String price) {
        BigDecimal parsed = new BigDecimal(price);

        return this.shampooRepository.countAllByPriceLessThan(parsed);
    }

    @Override
    public List<Shampoo> findAllByIngredientsIncluded(String... ingredients) {
        return this.shampooRepository.findAllByIngredientsIncluded(ingredients);
    }

    //@Override
    //public List<Shampoo> findAllByIngredientCountLessThan(int count) {
    //    return this.shampooRepository.findAllByIngredientCountLessThan(count);
    //}
}

package bg.softuni.nextleveltech.utils.validator;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtils {

    private final Validator validator;

    public ValidationUtils() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }
}

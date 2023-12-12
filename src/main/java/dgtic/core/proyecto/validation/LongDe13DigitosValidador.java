package dgtic.core.proyecto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongDe13DigitosValidador implements ConstraintValidator<LongDe13Digitos,Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if(!(aLong>=1000000000000L & aLong<=9999999999999L)){
            return false;
        }
        return true;
    }
}

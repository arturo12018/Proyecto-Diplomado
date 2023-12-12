package dgtic.core.proyecto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LongDe13DigitosValidador.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface  LongDe13Digitos {
    String message() default "Debe ser de 13 digitos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

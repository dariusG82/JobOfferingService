package eu.dariusgovedas.jobofferingservice.users.validation.annotations;

import eu.dariusgovedas.jobofferingservice.users.validation.validators.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Not unique email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

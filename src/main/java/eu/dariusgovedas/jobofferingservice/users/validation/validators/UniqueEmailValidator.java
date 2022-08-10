package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import eu.dariusgovedas.jobofferingservice.users.validation.annotations.UniqueEmail;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        return !userService.emailExists(email);
    }
}

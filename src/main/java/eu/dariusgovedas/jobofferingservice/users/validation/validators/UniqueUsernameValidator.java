package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import eu.dariusgovedas.jobofferingservice.users.validation.annotations.UniqueUsername;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        return !userService.usernameExists(username);
    }
}

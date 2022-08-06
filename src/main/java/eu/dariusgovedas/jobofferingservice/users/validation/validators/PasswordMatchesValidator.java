package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.entities.UserDTO;
import eu.dariusgovedas.jobofferingservice.users.validation.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO user = (UserDTO) obj;
        return user.getRepeatPassword() != null && user.getPassword().equals(user.getRepeatPassword());
    }
}

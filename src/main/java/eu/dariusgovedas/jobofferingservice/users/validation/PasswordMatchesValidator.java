package eu.dariusgovedas.jobofferingservice.users.validation;

import eu.dariusgovedas.jobofferingservice.users.entities.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getRepeatPassword());
    }
}

package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.validation.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null &&
                !email.trim().isEmpty() &&
                email.contains("@") &&
                email.contains(".") &&
                email.indexOf("@") < email.lastIndexOf(".") &&
                email.indexOf("@") == email.lastIndexOf("@") &&
                email.lastIndexOf(".") < email.length() - 1 ;
    }
}

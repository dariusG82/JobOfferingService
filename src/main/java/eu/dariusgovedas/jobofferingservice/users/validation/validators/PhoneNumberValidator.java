package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.validation.annotations.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    public static final String INTERNATIONAL = "+3706";
    public static final String LOCAL = "86";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

        return phoneNumber != null &&
                !phoneNumber.trim().isEmpty() &&
                isCorrectNumber(phoneNumber);
    }

    private boolean isCorrectNumber(String phoneNumber) {

        return isLocalNumber(phoneNumber) || isInternational(phoneNumber);
    }

    private boolean isLocalNumber(String number) {

        return (number.length() == 9) && number.startsWith(LOCAL);
    }

    private boolean isInternational(String number) {

        return (number.length() == 12) && number.startsWith(INTERNATIONAL);
    }

}

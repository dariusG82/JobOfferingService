package eu.dariusgovedas.jobofferingservice.commons.validators;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "emailAddress.required");

        if (user.getContactDetails().getEmailAddress() == null) {
            return;
        }

        if (!user.getContactDetails().getEmailAddress().contains("@") &&
                !user.getContactDetails().getEmailAddress().contains(".") &&
                !(user.getContactDetails().getEmailAddress().indexOf("@") < user.getContactDetails().getEmailAddress().indexOf("."))) {
            errors.rejectValue("emailAddress", "emailAddress.invalid",
                    new Object[]{user.getContactDetails().getEmailAddress()}, null);
        }
    }
}

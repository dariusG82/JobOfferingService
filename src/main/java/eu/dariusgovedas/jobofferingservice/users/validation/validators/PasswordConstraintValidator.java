package eu.dariusgovedas.jobofferingservice.users.validation.validators;

import eu.dariusgovedas.jobofferingservice.users.validation.annotations.ValidPassword;
import lombok.SneakyThrows;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        Properties properties = new Properties();

        if(getLocale().getLanguage().equals("en")){
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/passay.properties"), StandardCharsets.UTF_8));
        } else {
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/passay_lt.properties"), StandardCharsets.UTF_8));
        }
        PropertiesMessageResolver resolver = new PropertiesMessageResolver(properties);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
                new LengthRule(7, 20),

                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
//                new CharacterRule(EnglishCharacterData.Special, 1),
//
//                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 4, false),
//                new IllegalSequenceRule(EnglishSequenceData.Numerical, 4, false),
//                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 4, false),

                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}

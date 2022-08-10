package eu.dariusgovedas.jobofferingservice.users.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {

        if (attribute == null) {
            return null;
        }

        return attribute.getStatus();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return Stream.of(UserStatus.values())
                .filter(roleType -> roleType.getStatus().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

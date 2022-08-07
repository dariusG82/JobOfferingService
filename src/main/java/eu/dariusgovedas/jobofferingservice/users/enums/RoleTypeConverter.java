package eu.dariusgovedas.jobofferingservice.users.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {

    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getEnumValue();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        return Stream.of(RoleType.values())
                .filter(roleType -> roleType.getEnumValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

package eu.dariusgovedas.jobofferingservice.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String status;
}

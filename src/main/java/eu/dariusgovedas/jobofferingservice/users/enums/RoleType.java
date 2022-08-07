package eu.dariusgovedas.jobofferingservice.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    ADMIN("ADMIN"),
    FREELANCER("FREELANCER"),
    RECRUITER("RECRUITER"),
    DISABLED("DISABLED");

    private final String enumValue;
}

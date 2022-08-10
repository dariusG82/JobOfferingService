package eu.dariusgovedas.jobofferingservice.jobs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobStatus {

    ACTIVE("ACTIVE"),
    ACCEPTED("ACCEPTED"),
    CLOSED("CLOSED"),
    DISABLED("DISABLED");

    private final String enumValue;
}

package eu.dariusgovedas.jobofferingservice.jobs.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class JobStatusConverter implements AttributeConverter<JobStatus, String> {

    @Override
    public String convertToDatabaseColumn(JobStatus jobStatus) {

        if (jobStatus == null) {
            return null;
        }

        return jobStatus.getEnumValue();
    }

    @Override
    public JobStatus convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return Stream.of(JobStatus.values())
                .filter(jobStatus -> jobStatus.getEnumValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

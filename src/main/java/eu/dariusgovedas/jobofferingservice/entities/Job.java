package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private UUID jobId;
    private String jobTitle;
    private String jobType;
    private String deadline;

    private JobDetails jobDetails;
}

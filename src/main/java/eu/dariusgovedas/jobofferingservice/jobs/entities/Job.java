package eu.dariusgovedas.jobofferingservice.jobs.entities;

import eu.dariusgovedas.jobofferingservice.users.entities.Freelancer;
import eu.dariusgovedas.jobofferingservice.users.entities.Recruiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @Type(type = "uuid-char")
    private UUID jobID;
    private String jobTitle;
    private String jobType;
    private String deadline;

    @OneToOne
    @JoinColumn(name = "details_id")
    private JobDetails jobDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recruiter recruiter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Freelancer freelancer;
}

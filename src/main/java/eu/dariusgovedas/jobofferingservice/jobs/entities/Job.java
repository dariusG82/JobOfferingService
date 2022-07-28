package eu.dariusgovedas.jobofferingservice.jobs.entities;

import eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus;
import eu.dariusgovedas.jobofferingservice.users.entities.Freelancer;
import eu.dariusgovedas.jobofferingservice.users.entities.Recruiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal rating;
    private JobStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private JobDetails jobDetails;

    @ManyToOne
    private Recruiter recruiter;

    @ManyToOne
    private Freelancer freelancer;

    public void setRating(BigDecimal rating) {
        this.status = JobStatus.CLOSED;
        this.rating = rating;
    }
}

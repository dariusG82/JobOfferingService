package eu.dariusgovedas.jobofferingservice.jobs.entities;

import eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus;
import eu.dariusgovedas.jobofferingservice.users.entities.Freelancer;
import eu.dariusgovedas.jobofferingservice.users.entities.Recruiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @NotBlank
    private String jobTitle;

    @NotBlank
    private String jobType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Future
    private LocalDate deadline;

    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    private BigDecimal rating;

    private JobStatus status;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private JobDetails jobDetails;

    @ManyToOne
    private Recruiter recruiter;

    @ManyToOne
    private Freelancer freelancer;

    public void finishJob(BigDecimal rating) {
        this.status = JobStatus.CLOSED;
        this.rating = rating;
    }
}

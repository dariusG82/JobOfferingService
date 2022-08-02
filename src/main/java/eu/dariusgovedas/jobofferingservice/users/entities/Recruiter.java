package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String businessName;

    @OneToOne(mappedBy = "recruiter")
    private User user;

    @OneToMany(
            mappedBy = "recruiter",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Job> jobs;

    public void addJob(Job job){
        jobs.add(job);
        job.setRecruiter(this);
    }

    public void removeJob(Job job){
        jobs.remove(job);
        job.setRecruiter(null);
    }

    public int getTotalJobs(){
        return jobs.size();
    }

    public BigDecimal getTotalExpenses(){
        return jobs.stream()
                .filter(job -> job.getStatus().equals(JobStatus.CLOSED))
                .map(job -> job.getJobDetails().getSalary())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

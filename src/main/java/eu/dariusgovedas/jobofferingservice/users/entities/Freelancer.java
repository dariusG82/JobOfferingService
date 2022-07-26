package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int jobsFinished;
    private BigDecimal rating;
    private BigDecimal totalIncome;

    @OneToOne(mappedBy = "freelancer")
    private User user;

    @OneToMany(
            mappedBy = "freelancer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Job> jobs;

    public void addJob(Job job){
        jobs.add(job);
        job.setFreelancer(this);
    }

    public void removeJob(Job job){
        jobs.remove(job);
        job.setFreelancer(null);
    }
}

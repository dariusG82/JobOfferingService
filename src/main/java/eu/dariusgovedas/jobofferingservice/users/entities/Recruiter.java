package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
            orphanRemoval = true
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
}

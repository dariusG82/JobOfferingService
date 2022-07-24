package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String businessName;

    @OneToOne(mappedBy = "recruiter")
    private User user;

    @OneToMany(mappedBy = "recruiter")
    private Set<Job> jobs;
}

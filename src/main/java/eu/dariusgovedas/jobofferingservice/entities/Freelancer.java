package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    @Id
    private long id;
    private int jobsFinished;
    private BigDecimal rating;
    private BigDecimal totalIncome;

    @OneToOne(mappedBy = "freelancer")
    private AppUser appUser;

    @OneToMany(mappedBy = "freelancer")
    private Set<Job> jobs;
}

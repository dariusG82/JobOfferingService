package eu.dariusgovedas.jobofferingservice.jobs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal salary;

    @OneToOne(mappedBy = "jobDetails")
    private Job job;
}

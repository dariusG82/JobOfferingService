package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    private long id;
    private int jobsFinished;
    private BigDecimal rating;
    private BigDecimal totalIncome;

    private Set<Job> jobs;
}

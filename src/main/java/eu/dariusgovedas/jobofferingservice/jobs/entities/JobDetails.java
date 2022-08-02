package eu.dariusgovedas.jobofferingservice.jobs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
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

    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal salary;

    @OneToOne(mappedBy = "jobDetails")
    private Job job;
}

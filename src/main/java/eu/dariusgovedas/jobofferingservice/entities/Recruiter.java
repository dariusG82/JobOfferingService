package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {

    private long id;
    private String businessName;

    private Set<Job> jobs;
}

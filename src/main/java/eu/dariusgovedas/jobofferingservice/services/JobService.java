package eu.dariusgovedas.jobofferingservice.services;

import eu.dariusgovedas.jobofferingservice.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
}

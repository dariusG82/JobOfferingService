package eu.dariusgovedas.jobofferingservice.services;

import eu.dariusgovedas.jobofferingservice.entities.Job;
import eu.dariusgovedas.jobofferingservice.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Page<Job> getJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public void addNewJob(Job job) {
        jobRepository.save(job);
    }
}

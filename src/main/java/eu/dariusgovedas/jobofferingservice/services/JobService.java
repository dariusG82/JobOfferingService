package eu.dariusgovedas.jobofferingservice.services;

import eu.dariusgovedas.jobofferingservice.entities.Job;
import eu.dariusgovedas.jobofferingservice.exceptions.JobNotFoundException;
import eu.dariusgovedas.jobofferingservice.repositories.JobsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JobService {

    private final JobsRepository jobsRepository;

    public Page<Job> getJobs(Pageable pageable) {
        return jobsRepository.findAll(pageable);
    }

    public void createJob(Job job) {

        UUID jobId = UUID.randomUUID();
        job.setJobID(jobId);

        jobsRepository.save(job);
    }

    public void updateJob(Job job, UUID id) {
        job.setJobID(id);
        jobsRepository.save(job);
    }

    public Job deleteJobById(UUID id) {
        Job jobToDelete = getJobById(id);
        jobsRepository.delete(jobToDelete);
        return jobToDelete;
    }

    public Job getJobById(UUID id) {
        return jobsRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("",null));
    }

    public List<Job> searchByJobTitle(String title) {
        return jobsRepository.findByJobTitleContainingIgnoreCase(title);
    }
}

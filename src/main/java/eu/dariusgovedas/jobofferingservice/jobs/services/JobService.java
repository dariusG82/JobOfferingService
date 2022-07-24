package eu.dariusgovedas.jobofferingservice.jobs.services;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.exceptions.JobNotFoundException;
import eu.dariusgovedas.jobofferingservice.jobs.repositories.JobsRepository;
import eu.dariusgovedas.jobofferingservice.users.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JobService {

    private final JobsRepository jobsRepository;

    public Page<Job> getJobs(User user, Pageable pageable) {
        if(user != null){
            String userRole = user.getRole().getName();
            switch (userRole) {
                case "RECRUITER" -> {
                    return getRecruiterJobs(user, pageable);
                }
                case "FREELANCER" -> {
                    return getAvailableJobs(pageable);
                }
                default -> {
                    return getAllJobs(pageable);
                }
            }
        }
        return getAllJobs(pageable);
    }

    private Page<Job> getRecruiterJobs(User user, Pageable pageable) {
        List<Job> jobs = jobsRepository.findAll(pageable).stream()
                .filter(job -> job.getRecruiter().getUser().getUsername().equals(user.getUsername()))
                .toList();
        return new PageImpl<>(jobs, pageable, jobs.size());
    }

    private Page<Job> getAvailableJobs(Pageable pageable) {
        List<Job> jobs = jobsRepository.findAll(pageable).stream()
                .filter(job -> job.getRecruiter().getUser().getFreelancer() == null)
                .toList();
        return new PageImpl<>(jobs, pageable, jobs.size());
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
                .orElseThrow(() -> new JobNotFoundException("", null));
    }

    public Job addJobToFreelancer(UUID id, User user) {
        Job jobToAdd = getJobById(id);
        jobToAdd.setFreelancer(user.getFreelancer());
        jobsRepository.updateJob(jobToAdd.getJobID(), jobToAdd.getFreelancer().getId());

        return jobToAdd;
    }

    public Page<Job> searchByJobTitle(String title, Pageable pageable, User user) {
        if(title == null){
            title = "";
        }

        if(user != null && user.getFreelancer() != null){
            List<Job> jobs = jobsRepository.findInAvailableJobs(title.toUpperCase());
            return new PageImpl<>(jobs, pageable, jobs.size());
        }

        List<Job> jobs = jobsRepository.findByJobTitleContainingIgnoreCase(title);

        if (jobs.isEmpty() && title.isEmpty()) {
            return getJobs(user, pageable);
        }

        return new PageImpl<>(jobs, pageable, jobs.size());
    }

    private Page<Job> getAllJobs(Pageable pageable) {
        return jobsRepository.findAll(pageable);
    }


}

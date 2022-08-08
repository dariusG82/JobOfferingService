package eu.dariusgovedas.jobofferingservice.jobs.services;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.exceptions.JobNotFoundException;
import eu.dariusgovedas.jobofferingservice.jobs.repositories.JobsJPARepository;
import eu.dariusgovedas.jobofferingservice.users.entities.Freelancer;
import eu.dariusgovedas.jobofferingservice.users.entities.Recruiter;
import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.enums.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus.*;

@Service
@AllArgsConstructor
public class JobService {

    private final JobsJPARepository jobsRepository;

    public Page<Job> getJobs(User user, Pageable pageable) {
        if (user != null) {
            RoleType userRole = user.getRole().getName();
            switch (userRole) {
                case RECRUITER -> {
                    return getRecruiterJobs(pageable, user);
                }
                case FREELANCER -> {
                    return getAvailableJobs(pageable);
                }
                case ADMIN -> {
                    return getAllJobs(pageable);
                }
            }
        }
        return getAvailableJobs(pageable);
    }

    public Page<Job> getActiveFreelancerJobs(User user, Pageable pageable) {
        Long id = user.getFreelancer().getId();
        return jobsRepository.getAcceptedJobs(id, ACCEPTED, pageable);
    }

    public void createJob(Job job, User user) {

        UUID jobId = UUID.randomUUID();

        job.setJobID(jobId);
        job.setRecruiter(user.getRecruiter());
        job.setFreelancer(null);
        job.setStatus(ACTIVE);

        jobsRepository.save(job);
    }

    @Transactional
    public void updateJob(Job job, UUID id) {
        Job oldJob = getJobById(id);
        oldJob.setJobTitle(job.getJobTitle());
        oldJob.setJobType(job.getJobType());
        oldJob.setDeadline(job.getDeadline());
        oldJob.getJobDetails().setSalary(job.getJobDetails().getSalary());
        oldJob.getJobDetails().setDescription(job.getJobDetails().getDescription());
    }

    public Job deleteJobById(UUID id) {
        Job jobToDelete = getJobById(id);
        Recruiter recruiter = jobToDelete.getRecruiter();
        recruiter.removeJob(jobToDelete);
        Freelancer freelancer = jobToDelete.getFreelancer();
        if (freelancer != null) {
            freelancer.removeJob(jobToDelete);
        }
        jobsRepository.delete(jobToDelete);
        return jobToDelete;
    }

    public Job getJobById(UUID id) {
        return jobsRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("", null));
    }

    @Transactional
    public Job addJobToFreelancer(UUID id, User user) {
        Job jobToAdd = getJobById(id);
        jobToAdd.setFreelancer(user.getFreelancer());
        jobToAdd.setStatus(ACCEPTED);

        return jobToAdd;
    }

    public Page<Job> searchByJobTitle(String title, Pageable pageable, User user) {

        if (title == null) {
            title = "";
        }

        if (user != null && user.getFreelancer() != null) {
            return getAvailableJobs(title, pageable);
        }

        if (user != null && user.getRecruiter() != null) {
            return getRecruiterJobs(title, pageable, user);
        }

        return jobsRepository.findByJobTitleContainingIgnoreCase(title, pageable);
    }

    private Page<Job> getAvailableJobs(Pageable pageable) {
        return getAvailableJobs("", pageable);
    }

    private Page<Job> getAvailableJobs(String title, Pageable pageable) {
        return jobsRepository.findInAvailableJobs(title.toUpperCase(), pageable);
    }

    private Page<Job> getRecruiterJobs(Pageable pageable, User user) {
        return getRecruiterJobs("", pageable, user);
    }

    private Page<Job> getRecruiterJobs(String title, Pageable pageable, User user) {
        return jobsRepository.findInUserJobs(title.toUpperCase(), user.getRecruiter().getId(), pageable);
    }

    private Page<Job> getAllJobs(Pageable pageable) {
        return jobsRepository.findAll(pageable);
    }

    @Transactional
    public void finishJob(Job job, UUID id) {
        Job jobToRate = getJobById(id);
        jobToRate.finishJob(job.getRating());
    }

    public void deleteRecruiterJobs(User user) {
        List<Job> activeRecruiterJobs = jobsRepository.findActiveRecruiterJobs(user.getRecruiter().getId(), CLOSED);

        for(Job job : activeRecruiterJobs){
            job.setStatus(DISABLED);
            job.setFreelancer(null);
        }
    }
}

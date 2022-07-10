package eu.dariusgovedas.jobofferingservice.repositories;

import eu.dariusgovedas.jobofferingservice.entities.Job;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("mocked-db")
public class MockedRepository implements JobsRepository {

    private final List<Job> jobsList = new ArrayList<>();

    @Override
    public Page<Job> findAll(Pageable pageable) {
        return new PageImpl<>(jobsList, pageable, jobsList.size());
    }

    @Override
    public Job save(Job job) {
        Optional<Job> jobOptional = findById(job.getJobID());

        if (jobOptional.isPresent()) {
            updateJob(job);
        } else {
            addNewJob(job);
        }

        return job;
    }

    @Override
    public void delete(Job jobToDelete) {
        getIndex(jobToDelete.getJobID())
                .ifPresent(integer -> jobsList.remove(integer.intValue()));
    }

    @Override
    public Optional<Job> findById(UUID id) {
        return jobsList.stream()
                .filter(job -> job.getJobID().equals(id))
                .findFirst();
    }

    @Override
    public List<Job> findByJobTitleContainingIgnoreCase(String title) {

        return jobsList.stream()
                .filter(job -> job.getJobTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    private Optional<Integer> getIndex(UUID uuid) {

        for (int index = 0; index < jobsList.size(); index++) {
            if (jobsList.get(index).getJobID().equals(uuid)) {
                return Optional.of(index);
            }
        }

        return Optional.empty();
    }

    private void addNewJob(Job job) {
        jobsList.add(job);
    }

    private void updateJob(Job job) {
        getIndex(job.getJobID())
                .ifPresent(index -> jobsList.set(index, job));
    }
}

package eu.dariusgovedas.jobofferingservice.jobs.repositories;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobsRepository {
    Page<Job> findAll(Pageable pageable);

    Job save(Job job);

    void delete(Job jobToDelete);

    Optional<Job> findById(UUID id);

    List<Job> findByJobTitleContainingIgnoreCase(String title);
}

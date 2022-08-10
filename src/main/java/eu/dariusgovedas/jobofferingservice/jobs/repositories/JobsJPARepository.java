package eu.dariusgovedas.jobofferingservice.jobs.repositories;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobsJPARepository extends JpaRepository<Job, UUID> {

    @Query("FROM Job j WHERE j.rating IS NULL AND j.freelancer IS NULL AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInAvailableJobs(@Param("title") String title, Pageable pageable);

    @Query("FROM Job j WHERE j.recruiter.id=:id AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInUserJobs(@Param("title") String title, @Param("id") Long id, Pageable pageable);

    Page<Job> findByJobTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("FROM Job j WHERE j.freelancer.id=:id AND j.status=:status")
    Page<Job> getAcceptedJobs(@Param("id") Long id, @Param("status") JobStatus status, Pageable pageable);

    @Query("FROM Job j WHERE j.recruiter.id=:id AND NOT j.status=:status")
    List<Job> findActiveRecruiterJobs(@Param("id") Long id, @Param("status") JobStatus closed);
}

package eu.dariusgovedas.jobofferingservice.jobs.repositories;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface JobsJPARepository extends JpaRepository <Job, UUID> {

    @Query("FROM Job j WHERE j.freelancer IS NOT NULL AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInAvailableJobs(@Param("title") String title, Pageable pageable);

    @Query("FROM Job j WHERE j.recruiter.id=:id AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInUserJobs(@Param("title") String title, @Param("id") Long id, Pageable pageable);

    Page<Job> findByJobTitleContainingIgnoreCase(String title, Pageable pageable);
}

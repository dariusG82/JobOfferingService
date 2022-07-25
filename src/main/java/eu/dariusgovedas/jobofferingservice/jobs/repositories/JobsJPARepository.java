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

    @Modifying
    @Query("UPDATE Job j SET j.freelancer.id=:freelancerID WHERE j.jobID=:jobID")
    void updateJob(@Param("jobID") UUID jobId, @Param("freelancerID") long freelancerID);

    @Query("FROM Job j WHERE j.freelancer IS NOT NULL AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInAvailableJobs(@Param("title") String title, Pageable pageable);

    @Query("FROM Job j WHERE j.recruiter.id=:id AND UPPER(j.jobTitle) LIKE %:title%")
    Page<Job> findInUserJobs(@Param("title") String title, @Param("id") Long id, Pageable pageable);

    Page<Job> findByJobTitleContainingIgnoreCase(String title, Pageable pageable);

    @Modifying
    @Query("UPDATE Job j " +
            "SET j.jobTitle=:title, j.jobType=:type, j.deadline=:deadline, j.jobDetails.salary=:salary, j.jobDetails.description=:description " +
            "WHERE j.jobID=:id")
    void updateJob(
            @Param("id") UUID jobID,
            @Param("title") String jobTitle,
            @Param("type") String jobType,
            @Param("deadline") String deadline,
            @Param("salary") BigDecimal salary,
            @Param("description") String description);
}

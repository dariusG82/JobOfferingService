package eu.dariusgovedas.jobofferingservice.jobs.repositories;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobsJPARepository extends JpaRepository <Job, UUID>, JobsRepository {
    List<Job> findByJobTitleContainingIgnoreCase(String title);

    @Modifying
    @Query("UPDATE Job j SET j.freelancer.id=:freelancerID WHERE j.jobID=:jobID")
    void updateJob(@Param("jobID") UUID jobId, @Param("freelancerID") long freelancerID);

    @Query("FROM Job j WHERE j.freelancer IS NOT NULL AND UPPER(j.jobTitle) LIKE %:title%")
    List<Job> findInAvailableJobs(@Param("title") String title);

    @Query("FROM Job j WHERE j.recruiter.id=:id AND UPPER(j.jobTitle) LIKE %:title%")
    List<Job> findInUserJobs(@Param("title") String title, @Param("id") Long id);

    @Query("FROM Job j WHERE j.recruiter.id=:id")
    List<Job> findInUserJobs(@Param("id") Long id);
}

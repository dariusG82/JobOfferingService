package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    @Id
    @GeneratedValue
    private Long id;
    @DecimalMin(value = "0")
    private long jobsFinished;

    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal rating;

    @DecimalMin(value = "0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalIncome;

    @OneToOne(mappedBy = "freelancer")
    private User user;

    @OneToMany(
            mappedBy = "freelancer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Job> jobs;

    public void addJob(Job job){
        jobs.add(job);
        job.setFreelancer(this);
    }

    public void removeJob(Job job){
        jobs.remove(job);
        job.setFreelancer(null);
    }

    public BigDecimal getTotalIncome() {
        calculateTotalIncome();
        return totalIncome;
    }

    public BigDecimal getRating() {
        calculateRating();
        return rating;
    }

    public long getJobsFinished() {
        calculateFinishedJobs();
        return jobsFinished;
    }

    private void calculateRating(){
        if(jobsFinished == 0){
            rating = BigDecimal.ZERO;
            return;
        }
        BigDecimal totalRating = getTotalJobsRating();
        setRating(totalRating.divide(BigDecimal.valueOf(jobsFinished),2, RoundingMode.HALF_UP));
    }

    private BigDecimal getTotalJobsRating() {
        return jobs.stream()
                .filter(job -> job.getRating() != null)
                .map(Job::getRating)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void calculateFinishedJobs(){
        setJobsFinished(jobs.stream()
                .filter(job -> job.getStatus().equals(JobStatus.CLOSED))
                .count());
    }

    private void calculateTotalIncome(){
        setTotalIncome(jobs.stream()
                .filter(job -> job.getStatus().equals(JobStatus.CLOSED))
                .map(job -> job.getJobDetails().getSalary())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}

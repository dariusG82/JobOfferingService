package eu.dariusgovedas.jobofferingservice.controllers;

import eu.dariusgovedas.jobofferingservice.entities.Job;
import eu.dariusgovedas.jobofferingservice.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public String getJobs(Pageable pageable, Model model){
        Page<Job> jobs = jobService.getJobs(pageable);
        model.addAttribute("jobs", jobs);

        return "jobs";
    }

    @GetMapping("/create")
    public String openJobForm(Model model){

        model.addAttribute("job", new Job());
        return "jobForm";
    }

    @PostMapping("/create")
    public String createNewJob(Model model, Job job){

        jobService.addNewJob(job);
        model.addAttribute("jobs", jobService.getJobs(null));
        String message = "Job " + job.getJobTitle() + " successfully created";

        return "jobs";
    }

    @GetMapping("/{id}")
    public String openJobForm(@PathVariable UUID id, Model model){

        model.addAttribute("job", jobService.getJobById(id));

        return "jobForm";
    }

    @PostMapping("/{id}")
    public String updateJob(@PathVariable UUID id, Job job, Model model){

        jobService.updateJob(job, id);

        model.addAttribute("message", String.format("Job '%s' successfully updated", job.getJobTitle()));

        return getJobs(null, model);
    }

    @PostMapping("/{id}/delete")
    public String deleteJob(@PathVariable UUID id, Model model){

        Job job = jobService.deleteJobById(id);

        model.addAttribute("message", String.format("Job '%s' successfully deleted", job.getJobTitle()));

        return getJobs(null, model);
    }
}

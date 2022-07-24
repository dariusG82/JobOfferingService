package eu.dariusgovedas.jobofferingservice.jobs.controllers;

import eu.dariusgovedas.jobofferingservice.jobs.exceptions.JobNotFoundException;
import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/public/jobs")
    public String getJobs(Pageable pageable, Model model) {

        Page<Job> jobs = jobService.getJobs(pageable);
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/private/create")
    public String openJobForm(Model model) {

        model.addAttribute("job", new Job());
        return "jobForm";
    }

    @PostMapping("/private/create")
    public String createJob(Job job, RedirectAttributes redirectAttributes) {

        jobService.createJob(job);
        String message = "Job " + job.getJobTitle() + " successfully created";
        redirectAttributes.addFlashAttribute("jobs", message);
        return "redirect:/jobs";
    }

    @GetMapping("/private/{id}")
    public String openJob(@PathVariable UUID id, Model model) {

        model.addAttribute("job", jobService.getJobById(id));
        return "jobForm";
    }

    @PostMapping("/private/{id}")
    public String updateJob(@PathVariable UUID id, Job job, RedirectAttributes redirectAttributes) {

        jobService.updateJob(job, id);
        redirectAttributes.addAttribute("message", String.format("Job '%s' successfully updated", job.getJobTitle()));

        return "redirect:/jobs";
    }

    @PostMapping("/private/{id}/delete")
    public String deleteJob(@PathVariable UUID id, RedirectAttributes redirectAttributes) {

        Job job = jobService.deleteJobById(id);

        redirectAttributes.addAttribute("message", String.format("Job '%s' successfully deleted", job.getJobTitle()));

        return "redirect:/jobs";
    }

    @GetMapping("/public/search")
    public String search(@RequestParam(required = false) String title, Pageable pageable, Model model){

        model.addAttribute("jobs", jobService.searchByJobTitle(title, pageable));

        return "jobs";
    }

    @ExceptionHandler(JobNotFoundException.class)
    public String productNotFound(JobNotFoundException e, Model model) {

        model.addAttribute("messageCode", e.getMessage());
        model.addAttribute("productId", e.getJobID());

        return "error/productNotFoundPage";
    }
}

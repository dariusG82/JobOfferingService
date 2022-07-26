package eu.dariusgovedas.jobofferingservice.jobs.controllers;

import eu.dariusgovedas.jobofferingservice.jobs.exceptions.JobNotFoundException;
import eu.dariusgovedas.jobofferingservice.jobs.entities.Job;
import eu.dariusgovedas.jobofferingservice.jobs.services.JobService;
import eu.dariusgovedas.jobofferingservice.users.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String getJobs(Pageable pageable, Model model, @AuthenticationPrincipal User user) {

        Page<Job> jobs = jobService.getJobs(user, pageable);
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/private/jobs/create")
    public String openJobForm(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("job", new Job());
        return "jobForm";
    }

    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping("/private/jobs/create")
    public String createJob(Job job, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {

        jobService.createJob(job, user);
        String message = "Job " + job.getJobTitle() + " successfully created";
        redirectAttributes.addFlashAttribute("jobs", message);
        return "redirect:/public/jobs";
    }

    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/private/jobs/{id}")
    public String openJob(@PathVariable UUID id, Model model) {

        model.addAttribute("job", jobService.getJobById(id));

        return "jobForm";
    }

    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping("/private/jobs/{id}")
    public String updateJob(@PathVariable UUID id, Job job, RedirectAttributes redirectAttributes) {

        jobService.updateJob(job, id);
        redirectAttributes.addAttribute("message", String.format("Job '%s' successfully updated", job.getJobTitle()));

        return "redirect:/public/jobs";
    }

    @PreAuthorize("hasRole('FREELANCER')")
    @PostMapping("/private/jobs/{id}/apply")
    public String applyToJob(@PathVariable UUID id, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {

        Job job = jobService.addJobToFreelancer(id, user);

        redirectAttributes.addAttribute("message", String.format("Job '%s' successfully added to '%s' job list", job.getJobTitle(), user.getUsername()));

        return "redirect:/public/jobs";
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECRUITER')")
    @PostMapping("/private/jobs/{id}/delete")
    public String deleteJob(@PathVariable UUID id, RedirectAttributes redirectAttributes) {

        Job job = jobService.deleteJobById(id);

        redirectAttributes.addAttribute("message", String.format("Job '%s' successfully deleted", job.getJobTitle()));

        return "redirect:/public/jobs";
    }

    @GetMapping("/public/jobs/search")
    public String search(@RequestParam(required = false) String title, Pageable pageable, Model model, @AuthenticationPrincipal User user){

        model.addAttribute("jobs", jobService.searchByJobTitle(title, pageable, user));

        return "jobs";
    }

    @ExceptionHandler(JobNotFoundException.class)
    public String jobNotFound(JobNotFoundException e, Model model) {

        model.addAttribute("messageCode", e.getMessage());
        model.addAttribute("jobId", e.getJobID());

        return "error/jobNotFoundPage";
    }
}

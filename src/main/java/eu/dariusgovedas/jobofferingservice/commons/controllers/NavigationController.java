package eu.dariusgovedas.jobofferingservice.commons.controllers;

import eu.dariusgovedas.jobofferingservice.jobs.services.JobService;
import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class NavigationController {

    private UserService userService;
    private JobService jobService;

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/private/users")
    public String getUsersPage(Pageable pageable, Model model){

        model.addAttribute("freelancers", userService.getFreelancers(pageable));
        model.addAttribute("recruiters", userService.getRecruiters(pageable));

        return "users";
    }

    @GetMapping("/private/recruiter")
    public String getRecruiterInfoPage(Pageable pageable, Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("jobs", jobService.getJobs(user, pageable));

        return "userInfo";
    }

    @GetMapping("/private/freelancer")
    public String getFreelancerInfoPage(Pageable pageable, Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("jobs", jobService.getActiveFreelancerJobs(user, pageable));

        return "userInfo";
    }
}

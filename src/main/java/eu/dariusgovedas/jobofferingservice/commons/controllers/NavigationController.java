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

import static eu.dariusgovedas.jobofferingservice.users.enums.UserStatus.ACTIVE;

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

        model.addAttribute("freelancers", userService.getActiveFreelancers(ACTIVE,  pageable));
        model.addAttribute("recruiters", userService.getActiveRecruiters(ACTIVE, pageable));

        return "adminRecruiters";
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/admin/recruiters")
    public String getRecruitersManagingPage(Pageable pageable, Model model){

        model.addAttribute("recruiters", userService.getActiveRecruiters(ACTIVE, pageable));

        return "adminRecruiters";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/admin/freelancers")
    public String getFreelancersManagingPage(Pageable pageable, Model model){

        model.addAttribute("freelancers", userService.getActiveFreelancers(ACTIVE, pageable));

        return "adminFreelancers";
    }
}

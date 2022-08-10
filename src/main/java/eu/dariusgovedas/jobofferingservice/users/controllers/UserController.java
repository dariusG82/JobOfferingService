package eu.dariusgovedas.jobofferingservice.users.controllers;

import eu.dariusgovedas.jobofferingservice.jobs.services.JobService;
import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.entities.userdata.UserDTO;
import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static eu.dariusgovedas.jobofferingservice.users.enums.UserStatus.ACTIVE;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private JobService jobService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/admin/recruiters")
    public String getRecruitersManagingPage(Pageable pageable, Model model) {

        model.addAttribute("recruiters", userService.getActiveRecruiters(ACTIVE, pageable));

        return "adminRecruiters";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/admin/freelancers")
    public String getFreelancersManagingPage(Pageable pageable, Model model) {

        model.addAttribute("freelancers", userService.getActiveFreelancers(ACTIVE, pageable));

        return "adminFreelancers";
    }

    @GetMapping("/private/recruiter")
    public String getRecruiterInfoPage(Pageable pageable, Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("user", user);
        model.addAttribute("jobs", jobService.getJobs(user, pageable));

        return "userInfo";
    }

    @GetMapping("/private/freelancer")
    public String getFreelancerInfoPage(Pageable pageable, Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("user", user);
        model.addAttribute("jobs", jobService.getActiveFreelancerJobs(user, pageable));

        return "userInfo";
    }

    @GetMapping("/public/signUpForm")
    public String openSignUpForm(Model model) {

        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);

        return "signUpForm";
    }

    @PostMapping("/public/signUpForm")
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "signUpForm";
        }

        User user = userService.registerNewUser(userDTO);

        redirectAttributes.addFlashAttribute("message", String.format("User %s is successfully registered", user.getUsername()));

        return "redirect:/index";
    }

    @PostMapping("/private/admin/freelancer/{username}/delete")
    public String deleteFreelancer(@PathVariable String username, RedirectAttributes redirectAttributes) {

        User user = userService.deleteFreelancer(username);

        redirectAttributes.addFlashAttribute("message", String.format("User %s is successfully deleted", user.getUsername()));

        return "redirect:/private/admin/freelancers";
    }

    @PostMapping("/private/admin/recruiter/{username}/delete")
    public String deleteRecruiter(@PathVariable String username, RedirectAttributes redirectAttributes) {

        User user = userService.deleteRecruiter(username);

        redirectAttributes.addFlashAttribute("message", String.format("User %s is successfully deleted", user.getUsername()));

        return "redirect:/private/admin/recruiters";
    }
}

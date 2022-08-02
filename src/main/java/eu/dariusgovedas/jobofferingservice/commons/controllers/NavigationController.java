package eu.dariusgovedas.jobofferingservice.commons.controllers;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class NavigationController {

    private UserService userService;

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

    @GetMapping("/private/user")
    public String getFreelancerInfo(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);

        return "userInfo";
    }
}

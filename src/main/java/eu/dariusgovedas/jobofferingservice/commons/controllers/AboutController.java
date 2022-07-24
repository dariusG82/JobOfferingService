package eu.dariusgovedas.jobofferingservice.commons.controllers;

import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/about")
public class AboutController {

    private UserService userService;

    @GetMapping
    public String getAbout(Pageable pageable, Model model){

        model.addAttribute("freelancers", userService.getFreelancers(pageable));
        model.addAttribute("recruiters", userService.getRecruiters(pageable));

        return "about";
    }
}

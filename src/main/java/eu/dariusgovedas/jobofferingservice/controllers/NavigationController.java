package eu.dariusgovedas.jobofferingservice.controllers;

import eu.dariusgovedas.jobofferingservice.entities.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/about")
    public String getAboutUsPage(){
        return "about";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signUpForm")
    public String getSignUpPage(Model model){

        model.addAttribute("appUser", new AppUser());

        return "signUpForm";
    }
}

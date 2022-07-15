package eu.dariusgovedas.jobofferingservice.controllers;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/signUp")
    public String getSignUpPage(){
        return "signUpForm";
    }
}

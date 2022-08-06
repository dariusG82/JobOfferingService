package eu.dariusgovedas.jobofferingservice.users.controllers;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.entities.UserDTO;
import eu.dariusgovedas.jobofferingservice.users.services.UserService;
import eu.dariusgovedas.jobofferingservice.users.validation.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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
}

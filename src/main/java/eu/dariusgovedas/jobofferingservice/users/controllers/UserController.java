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
//    private final RegistrationValidator registrationValidator;
//
//    @InitBinder(value = "user")
//    void initStudentValidator(WebDataBinder binder){
//        binder.addValidators(registrationValidator);
//    }

    @GetMapping("/public/signUpForm")
    public String openSignUpForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "signUpForm";
    }

    @PostMapping("/public/signUpForm")
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult errors, RedirectAttributes redirectAttributes){

        if(errors.hasErrors()){
            return "signUpForm";
        }

        try {
            User registered = userService.registerNewUser(userDTO);
        } catch (UserAlreadyExistException uaeEx){
            redirectAttributes.addFlashAttribute("message", "Account for this username/email already exist");
        }

//        userService.createUser(user);
//
//        redirectAttributes.addFlashAttribute("message",
//                String.format("User '%s' succsessfully created!", user.getUsername()));

        return "redirect:/index";
    }
}

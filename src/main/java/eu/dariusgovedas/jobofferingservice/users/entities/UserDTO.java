package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.users.validation.annotations.PasswordMatches;
import eu.dariusgovedas.jobofferingservice.users.validation.annotations.PhoneNumber;
import eu.dariusgovedas.jobofferingservice.users.validation.annotations.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@PasswordMatches(message = "{PasswordMatches.user}")
// User Data Transfer Object
public class UserDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String repeatPassword;

    @NotEmpty
    @ValidEmail
    private String emailAddress;

    @PhoneNumber
    private String phoneNumber;

    private String businessName;
}

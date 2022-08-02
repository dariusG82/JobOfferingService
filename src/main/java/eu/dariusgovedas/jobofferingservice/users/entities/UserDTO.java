package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.users.validation.PasswordMatches;
import eu.dariusgovedas.jobofferingservice.users.validation.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
// User Data Transfer Object
public class UserDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String repeatPassword;

    @Email
    private String emailAddress;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String businessName;
}

package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.users.validation.annotations.*;
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
    @UniqueUsername
    private String username;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    private String repeatPassword;

    @NotEmpty
    @ValidEmail
    @UniqueEmail
    private String emailAddress;

    @NotEmpty
    @PhoneNumber
    private String phoneNumber;

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    private Long houseNr;

    private Long flatNr;

    private String businessName;
}

package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    private long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
}

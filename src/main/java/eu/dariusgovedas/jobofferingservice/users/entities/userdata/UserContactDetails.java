package eu.dariusgovedas.jobofferingservice.users.entities.userdata;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContactDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String emailAddress;
    private String webPageAddress;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private HomeAddress homeAddress;

    @OneToOne(mappedBy = "contactDetails")
    private User user;
}

package eu.dariusgovedas.jobofferingservice.users.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeAddress {

    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String city;
    private String street;
    private Long houseNumber;
    private Long flatNumber;

    public String getHomeAddress() {
        String houseAddress = country + ", " + city + ", " + street + " street " + houseNumber;
        if(flatNumber == null){
            return houseAddress;
        } else {
            return houseAddress + " - " + flatNumber;
        }
    }
}

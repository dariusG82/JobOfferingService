package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.users.enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue
    private long id;
    private RoleType name;

    @Override
    public String getAuthority(){
        return ROLE_PREFIX + name.getEnumValue();
    }
}

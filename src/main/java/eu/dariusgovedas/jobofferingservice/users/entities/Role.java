package eu.dariusgovedas.jobofferingservice.users.entities;

import eu.dariusgovedas.jobofferingservice.users.enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @Override
    public String getAuthority(){
        return ROLE_PREFIX + name.getEnumValue();
    }
}

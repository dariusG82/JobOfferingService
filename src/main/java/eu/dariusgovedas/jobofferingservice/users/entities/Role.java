package eu.dariusgovedas.jobofferingservice.users.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    private String name;

    @Override
    public String getAuthority(){
        return ROLE_PREFIX + name;
    }
}

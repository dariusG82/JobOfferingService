package eu.dariusgovedas.jobofferingservice.commons.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "company")
public class CompanyInfo {

    private String name;
    private String address;
    private String bank;
    private String iban;
}


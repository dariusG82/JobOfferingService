package eu.dariusgovedas.jobofferingservice;

import java.util.UUID;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(UUID uuid){
        super(uuid.toString());
    }
}

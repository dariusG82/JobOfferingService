package eu.dariusgovedas.jobofferingservice.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class JobNotFoundException extends RuntimeException {

    private final UUID jobID;

    public JobNotFoundException(String messageCode, UUID jobID){
        super(messageCode);
        this.jobID = jobID;
    }
}

package eu.dariusgovedas.jobofferingservice.users.validation;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("Email and/or username already exist");
    }
}
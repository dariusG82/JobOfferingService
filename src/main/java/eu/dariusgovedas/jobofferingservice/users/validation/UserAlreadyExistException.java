package eu.dariusgovedas.jobofferingservice.users.validation;

public class UserAlreadyExistException extends RuntimeException{
    private final String message;

    public UserAlreadyExistException(String s) {
        this.message = super.getMessage() + s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package spring.cwiczenia;

public class InvalidValueException extends RuntimeException {
    private String description;
    public InvalidValueException(String description) {
        super();
        this.description = description;
    }
}

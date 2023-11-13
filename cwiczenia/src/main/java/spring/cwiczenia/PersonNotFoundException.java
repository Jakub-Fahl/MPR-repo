package spring.cwiczenia;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Person not found");
    }
}

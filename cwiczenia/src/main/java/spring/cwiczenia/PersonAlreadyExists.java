package spring.cwiczenia;

public class PersonAlreadyExists extends RuntimeException {
    public PersonAlreadyExists() {
        super("Person with this id already exists!");
    }
}

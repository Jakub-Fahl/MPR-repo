package spring.cwiczenia;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    public Person findByName(String name);
    public Person findByNameAndLastName(String name, String lastName);
}

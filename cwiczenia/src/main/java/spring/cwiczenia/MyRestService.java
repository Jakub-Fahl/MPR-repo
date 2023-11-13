package spring.cwiczenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {

    PersonRepository userRepository;
@Autowired
    public MyRestService(PersonRepository userRepository) {
        this.userRepository = userRepository;
        this.userRepository.save(new Person("Daniel", "EEE"));
        this.userRepository.save(new Person("Dawid", "Klaun"));
    }

    public Person findByName(String name) {
        return this.userRepository.findByName(name);
    }
    public Optional<Person>findById(Long id) {
        Optional<Person> person = this.userRepository.findById(id);
        if (person.isPresent()) {
            return person;
        } else {
            throw new PersonNotFoundException();
        }
    }
    private Person findByNameAndLastName(String name, String lastName) {
        return this.userRepository.findByNameAndLastName(name, lastName);
    }
    public List<Person>findAll() {
        return (List<Person>) this.userRepository.findAll();
    }

    public Person addPerson(String name, String lastName) {
        Person existingPerson = findByNameAndLastName(name, lastName);
        if(existingPerson != null) {
            throw new PersonAlreadyExists();
        }
        else
        {
            Person newOne = new Person();
            newOne.setName(name);
            newOne.setLastName(lastName);
            userRepository.save(newOne);
            return newOne;
        }
    }
    public boolean updatePerson(Long id, String name, String lastName) {
        Optional<Person> personOptional = userRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            if (name.isEmpty())
                throw new InvalidValueException("Person name is illegal");

            if (lastName.isEmpty())
                throw new InvalidValueException("Person lastName is illegal");
            person.setName(name);
            person.setLastName(lastName);
            userRepository.save(person);
            return true;
        }
        return false;
    }
    public boolean deletePerson(long id) {
        Optional<Person> existingPerson = userRepository.findById(id);
        if(existingPerson.isPresent()) {
            userRepository.delete(existingPerson.get());
            return true;
        }
        else {
            throw new PersonNotFoundException();
        }
    }




}

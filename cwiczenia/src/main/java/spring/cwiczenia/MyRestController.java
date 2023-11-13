package spring.cwiczenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {

    private final  MyRestService mprService;

    @Autowired
    public MyRestController(MyRestService service) {
        this.mprService = service;
    }

    @GetMapping("name/{name}")
    public Person getByName(@PathVariable("name") String name) {
        return mprService.findByName(name);
    }
    @GetMapping("person/get/{id}")
    public Optional<Person> getById(@PathVariable("id")Long id) {
        return mprService.findById(id);
    }
    @GetMapping("person/get/all")
    public List<Person> getAll() {
        return mprService.findAll();
    }
    @PostMapping("person/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person body) {
        Person added = mprService.addPerson(body.getName(), body.getLastName());
        if(added != null) {
            return new ResponseEntity<>(added, HttpStatus.CREATED);
        }
        return null;
    }
    @PatchMapping("person/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Person body) {
        boolean result = mprService.updatePerson(id, body.getName(), body.getLastName());
        if(result) {
            return new ResponseEntity<>("Zaktualizowano osobę", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Nie znaleziono osoby", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("person/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        boolean result = mprService.deletePerson(id);
        if(result) {
            return new ResponseEntity<>("Osoba usunięta", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Nie znaleziono osoby", HttpStatus.NOT_FOUND);
        }
    }




}

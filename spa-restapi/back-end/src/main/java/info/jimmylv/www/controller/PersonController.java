package info.jimmylv.www.controller;

import info.jimmylv.www.domain.Person;
import info.jimmylv.www.resources.PersonResource;
import info.jimmylv.www.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Component
public class PersonController {
    private PersonService personService;

    @Autowired
    PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    public List<Person> getPersonsList() {
        return personService.findAll();
    }

    @RequestMapping("/person/")
    public Person getPersonById(@RequestParam(value = "id") final String id) {
        return personService.findById(id);
    }

    @RequestMapping("/personss/")
    public Person getPersonByFirstName(@RequestParam(value = "firstName") final String firstName) {
        return personService.findByFirstName(firstName);
    }

    @RequestMapping(value = "/saveperson", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody final Person person) {
        Person savedPerson = personService.save(person);

        HttpHeaders httpHeaders = new HttpHeaders();
        Link forOnePerson = new PersonResource(savedPerson).getLink("self");
        httpHeaders.setLocation(URI.create(forOnePerson.getHref()));

//        httpHeaders.setLocation(
//            ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("person/{id}").buildAndExpand(savedPerson.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

}
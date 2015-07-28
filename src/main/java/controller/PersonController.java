package controller;

import domain.Person;
import resources.PersonResource;
import service.PersonService;
import service.PersonServiceImpl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class PersonController
{
    private PersonService personService;

    /**
     * @param personService
     */
    @Autowired
    PersonController(
        final PersonService personService)
    {
        this.personService = personService;
    }

    /**
     * @return
     */
    @RequestMapping("/persons")
    public List<Person> findAll()
    {
        return personService.findAll();
    }

    /**
     * @return
     */
    @RequestMapping("/person/")
    public Person findById(@RequestParam(value = "id") final String id)
    {
        return personService.findById(id);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/saveperson", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody final Person person)
    {
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
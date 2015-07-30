package info.jimmylv.www.resources;

import info.jimmylv.www.controller.PersonController;
import info.jimmylv.www.domain.Person;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonResource extends ResourceSupport {

    private final Person person;

    public PersonResource(final Person person) {
        this.person = person;
        this.add(new Link("htgd", "person-link"));
        this.add(linkTo(PersonController.class).withRel("bookmarks"));
        this.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
    }

    public Person getPerson() {
        return person;
    }
}

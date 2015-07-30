package info.jimmylv.www.controller;

import info.jimmylv.www.domain.Person;
import info.jimmylv.www.service.PersonService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by Jing on 7/29/15.
 */
@RunWith(JUnitParamsRunner.class)
public class PersonControllerTest {

    @Mock
    PersonService personService;
    Person jimmy;
    Person kimmy;

    @InjectMocks
    PersonController personController;

    private String jimmy_id;
    private String jimmy_first_name;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        List<Person> persons = new ArrayList<>();
        when(personService.findAll()).thenReturn(persons);

        jimmy_id = "1234567890";
        jimmy_first_name = "jimmy";

        when(personService.findById(jimmy_id)).thenReturn(jimmy);
        when(personService.findByFirstName(jimmy_first_name)).thenReturn(jimmy);
        when(personService.findByFirstName("kimmy")).thenReturn(kimmy);
    }

    @Test
    public void should_get_person_list() throws Exception {
        personController.getPersonsList();

        verify(personService, times(1)).findAll();
    }

    @Test
    public void should_get_person_when_give_id() throws Exception {
        assertThat(personController.getPersonById(jimmy_id), is(jimmy));
        verify(personService).findById(jimmy_id);

    }

    @Test
    public void should_get_person_when_give_first_name() throws Exception {
        assertThat(personController.getPersonByFirstName(jimmy_first_name), is(jimmy));
        verify(personService).findByFirstName(jimmy_first_name);
    }

/*
    @Test
    @Parameters
    public void should_get_different_person_when_give_different_firstName(String firstName, Person person) {
        assertThat(personController.getPersonByFirstName(firstName), is(person));
    }

    private Object parametersForShould_get_different_person_when_give_different_firstName() {
        return $(
                $("jimmy", jimmy),
                $("kimmy", jimmy)
        );
    }
*/

    @Test
    public void should_save_person_when_give_a_person() throws Exception {

    }
}
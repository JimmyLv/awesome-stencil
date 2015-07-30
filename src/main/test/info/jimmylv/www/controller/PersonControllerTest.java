package info.jimmylv.www.controller;

import info.jimmylv.www.domain.Person;
import info.jimmylv.www.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Created by Jing on 7/29/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @Mock
    PersonService personService;
    Person person;

    @InjectMocks
    PersonController personController;
    private String jimmy_id;
    private String jimmy_first_name;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        List<Person> persons = new ArrayList<>();
        when(personService.findAll()).thenReturn(persons);

        jimmy_id = "1234567890";
        jimmy_first_name = "jimmy";
        when(personService.findById(jimmy_id)).thenReturn(person);
        when(personService.findByFirstName(jimmy_first_name)).thenReturn(person);
    }

    @Test
    public void should_get_person_list() throws Exception {
        personController.findAll();

        verify(personService, times(1)).findAll();
    }

    @Test
    public void should_get_person_when_give_id() throws Exception {
        assertThat(personController.findById(jimmy_id), is(person));
        verify(personService).findById(jimmy_id);

    }

    @Test
    public void should_get_person_when_give_first_name() throws Exception {
        assertThat(personController.findByFirstName(jimmy_first_name), is(person));
        verify(personService).findByFirstName(jimmy_first_name);
    }

    @Test
    public void should_save_person_when_give_a_person() throws Exception {

    }
}
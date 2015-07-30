package info.jimmylv.www.service;

import info.jimmylv.www.domain.Person;
import info.jimmylv.www.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class PersonServiceImpl implements PersonService
{
    private PersonRepository personRepository;
    
    @Autowired
    public PersonServiceImpl(final PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> findAll()
    {
        return personRepository.findAll();
    }

    @Override
    public Person save(final Person person)
    {
        return personRepository.save(person);
    }

    @Override
    public Person findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }


    @Override
    public Person findById(final String id) {
        return personRepository.findById(id);
    }

}
import domain.Person;
import repository.PersonRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gpotes
 *
 */
@Component
@Transactional
public class PersonServiceImpl implements IPersonService
{
    private PersonRepository personRepository;
    
    @Autowired
    public PersonService(final PersonRepository personRepository)
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
    public Person findById(final String id) {
        return personRepository.findById(id);
    }

}
package info.jimmylv.www.repository;

import info.jimmylv.www.domain.Person;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String>
{
    public Person findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);
    public Person findById(String id);
}
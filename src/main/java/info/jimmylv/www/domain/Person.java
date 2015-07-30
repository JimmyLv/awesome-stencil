package info.jimmylv.www.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Person
{
    @Id
    private String id;
    @Field(value = "first_name")
    private String firstName;
    @Field(value = "last_name")
    private String lastName;

    private String profession;
    private int location[];
    private List<Company> companies;

    public Person() {}

    public Person(
        String firstName, String lastName, String profession, int [] location, List<Company> companies)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
        this.location = location;
        this.companies = companies;
    }

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }


    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public int[] getLocation()
    {
        return location;
    }

    public void setLocation(int[] location)
    {
        this.location = location;
    }

    public List<Company> getCompanies()
    {
        return companies;
    }

    public void setCompanies(List<Company> companies)
    {
        this.companies = companies;
    }

}
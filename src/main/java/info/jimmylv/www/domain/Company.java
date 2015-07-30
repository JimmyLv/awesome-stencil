package info.jimmylv.www.domain;

import org.springframework.data.mongodb.core.index.Indexed;

public class Company
{
    @Indexed (unique = true)
    private String orgName;
    private String headquarter;

    public Company() {}

    public Company(String orgName, String headquarter) {
        this.orgName = orgName;
        this.headquarter = headquarter;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }
}
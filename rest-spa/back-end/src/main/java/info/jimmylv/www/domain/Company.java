package info.jimmylv.www.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Company {
    @Indexed(unique = true)
    private String orgName;
    private String headquarter;
    @DateTimeFormat
    private Date joinTime;

    public Company() {
    }

    public Company(String orgName, String headquarter, Date joinTime) {
        this.orgName = orgName;
        this.headquarter = headquarter;
        this.joinTime = joinTime;
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

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
package com.sparta.miniproject.fixture;

import com.sparta.miniproject.entity.Company;

import java.util.List;

public class CompanyFixture {
    public static Company caseWhichRegisteredAtFirst() {
        Company entity = new Company();
        entity.setCompanyName("mock companyName1");
        entity.setLocation("mock location1");
        entity.setSales("mock sales1");
        entity.setLogoUrl("mock logo");
        return entity;
    }

    public static Company caseWhichRegisteredAtSecond() {
        Company entity = new Company();
        entity.setCompanyName("mock companyName2");
        entity.setLocation("mock location2");
        entity.setSales("mock sales2");
        entity.setLogoUrl("mock logo");
        return entity;
    }

    public static List<Company> caseList() {
        return List.of(caseWhichRegisteredAtFirst(), caseWhichRegisteredAtSecond());
    }
}

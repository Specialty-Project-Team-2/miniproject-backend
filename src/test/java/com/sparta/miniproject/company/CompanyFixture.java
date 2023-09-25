package com.sparta.miniproject.company;

import java.util.List;

public class CompanyFixture {
    public static Company case1() {
        Company entity = new Company();
        entity.setCompanyName("mock companyName1");
        entity.setLocation("mock location1");
        entity.setSales("mock sales1");
        entity.setLogoUrl("mock logo");
        return entity;
    }

    public static Company case2() {
        Company entity = new Company();
        entity.setCompanyName("mock companyName2");
        entity.setLocation("mock location2");
        entity.setSales("mock sales2");
        entity.setLogoUrl("mock logo");
        return entity;
    }

    public static List<Company> caseList1() {
        return List.of(case1(), case2());
    }
}

package com.sparta.miniproject.company.query;

import com.sparta.miniproject.company.Company;

import java.util.List;

public interface SearchCompanyRepository {
    List<Company> searchAllBy(String keywordCompanyName);
}

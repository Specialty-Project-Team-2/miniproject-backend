package com.sparta.miniproject.company.query;

import com.sparta.miniproject.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchCompanyRepository {
    Page<Company> searchAllBy(String keywordCompanyName, Pageable pageable);
}

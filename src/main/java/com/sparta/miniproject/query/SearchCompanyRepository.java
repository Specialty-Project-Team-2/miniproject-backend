package com.sparta.miniproject.query;

import com.sparta.miniproject.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchCompanyRepository {
    Page<Company> searchAllBy(String keywordCompanyName, Pageable pageable);
}

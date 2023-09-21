package com.sparta.miniproject.company;

import com.sparta.miniproject.company.query.SearchCompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>
        , SearchCompanyRepository {
}

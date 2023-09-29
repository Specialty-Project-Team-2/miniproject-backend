package com.sparta.miniproject.repository;

import com.sparta.miniproject.query.SearchCompanyRepository;
import com.sparta.miniproject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>
        , SearchCompanyRepository {
}

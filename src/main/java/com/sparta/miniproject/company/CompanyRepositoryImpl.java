package com.sparta.miniproject.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.miniproject.company.query.CompanyQueryUtil;
import com.sparta.miniproject.company.query.SearchCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.miniproject.company.QCompany.company;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements SearchCompanyRepository {
    private final JPAQueryFactory factory;

    @Override
    public List<Company> searchAllBy(String keywordCompanyName) {
        return factory
                .select(company).from(company)
                .where(
                        CompanyQueryUtil.companyNameContains(company, keywordCompanyName)
                )
                .fetch();
    }
}

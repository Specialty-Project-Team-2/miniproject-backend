package com.sparta.miniproject.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.miniproject.company.query.CompanyQueryUtil;
import com.sparta.miniproject.company.query.SearchCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.miniproject.company.QCompany.company;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements SearchCompanyRepository {
    private final JPAQueryFactory factory;

    @Override
    public Page<Company> searchAllBy(String keywordCompanyName, Pageable pageable) {
        // 실제 조회 쿼리
        List<Company> result = factory
                .select(company).from(company)
                .where(
                        CompanyQueryUtil.companyNameContains(company, keywordCompanyName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // countQuery
        Long count = factory
                .select(company.count()).from(company)
                .where(
                        CompanyQueryUtil.companyNameContains(company, keywordCompanyName)
                )
                .fetchOne();

        return new PageImpl<>(result, pageable, count);
    }
}

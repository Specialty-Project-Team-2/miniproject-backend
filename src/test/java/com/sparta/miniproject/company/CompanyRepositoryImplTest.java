package com.sparta.miniproject.company;

import com.sparta.miniproject.tool.EnableQuerydslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@EnableQuerydslTest
@DataJpaTest
class CompanyRepositoryImplTest {
    @Autowired
    private CompanyRepositoryImpl companyRepositoryImpl;
    @Autowired
    private CompanyRepository companyRepository;

    @LoadTestcase1
    @Test
    @DisplayName("[정상 작동] 주어진 키워드에 맞는 데이터 꺼내오기.")
    void searchAllBy() {
        // given
        String keywordCompanyName = "kakao";

        // when
        List<Company> companies = companyRepositoryImpl.searchAllBy(keywordCompanyName);

        // then
        int totalSizeOfTestcase = 3;

        assertThat(companies.size()).isEqualTo(totalSizeOfTestcase);

        for (Company company : companies) {
            String companyName = company.getCompanyName();

            log.info(companyName);
            assertThat(companyName).containsIgnoringCase(keywordCompanyName);
        }
    }

    @LoadTestcase1
    @Test
    @DisplayName("[정상 작동] 키워드가 존재하지 않으면 전체 조회.")
    void searchAllByNoKeyword() {
        // given
        String keywordCompanyName = null;

        // when
        List<Company> companies = companyRepositoryImpl.searchAllBy(keywordCompanyName);

        // then
        int totalSizeOfTestcase = 6;

        assertThat(companies.size()).isEqualTo(totalSizeOfTestcase);

        for (Company company : companies) {
            log.info(company.getCompanyName());
        }
    }

    @LoadTestcase1
    @Test
    @DisplayName("[정상 작동] 그냥 일괄 조회")
    void readAll() {
        // given
        String keywordCompanyName = null;

        // when
        List<Company> companies = companyRepository.findAll();

        // then
        int totalSizeOfTestcase = 6;

        assertThat(companies.size()).isEqualTo(totalSizeOfTestcase);

        for (Company company : companies) {
            log.info(company.getCompanyName());
        }
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Sql(
            scripts = {"classpath:testcase1-for-company-repository.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    private @interface LoadTestcase1 {}
}
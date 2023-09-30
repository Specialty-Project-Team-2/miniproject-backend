package com.sparta.miniproject.company;

import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.repository.CompanyRepository;
import com.sparta.miniproject.repository.CompanyRepositoryImpl;
import com.sparta.miniproject.util.EnableQuerydslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @LoadTestcase
    @Test
    @DisplayName("[정상 작동] 주어진 키워드에 맞는 데이터 꺼내오기.")
    void searchAllBy() {
        // given
        String keywordCompanyName = "kakao";
        Pageable pageable = PageRequest.of(0, 5);

        // when
        Page<Company> companies = companyRepositoryImpl.searchAllBy(keywordCompanyName, pageable);

        // then
        int totalSizeOfTestcase = 3;

        assertThat(companies.getTotalElements()).isEqualTo(totalSizeOfTestcase);

        for (Company company : companies) {
            String companyName = company.getCompanyName();

            log.info(companyName);
            assertThat(companyName).containsIgnoringCase(keywordCompanyName);
        }
    }

    @LoadTestcase
    @Test
    @DisplayName("[정상 작동] 키워드가 존재하지 않으면 전체 조회.")
    void searchAllByNoKeyword() {
        // given
        String keywordCompanyName = null;
        Pageable pageable = PageRequest.of(0, 5);

        // when
        Page<Company> companies = companyRepositoryImpl.searchAllBy(keywordCompanyName, pageable);

        // then
        int totalSizeOfTestcase = 6;

        assertThat(companies.getTotalElements()).isEqualTo(totalSizeOfTestcase);

        for (Company company : companies) {
            log.info(company.getCompanyName());
        }
    }

    @LoadTestcase
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

    @LoadTestcase
    @Test
    @DisplayName("[정상 작동] 페이징 테스트.")
    void searchAllByWithPageable() {
        // given
        String keywordCompanyName = "a";
        Pageable pageable = PageRequest.of(0, 2);

        // when
        Page<Company> companies = companyRepositoryImpl.searchAllBy(keywordCompanyName, pageable);

        // then
        int totalSizeOfTestcase = 6;
        int totalPageOfTestcase = 3;

        // 총 갯수 확인. - countQuery 확인.
        assertThat(companies.getTotalElements()).isEqualTo(totalSizeOfTestcase);
        // 총 페이지 수 확인.
        assertThat(companies.getTotalPages()).isEqualTo(totalPageOfTestcase);
        // 첫 페이지 감지 유무.
        assertThat(companies.getPageable().hasPrevious()).isFalse();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Sql(
            scripts = {"classpath:testcase-for-company-repository-impl-test.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    private @interface LoadTestcase {}
}
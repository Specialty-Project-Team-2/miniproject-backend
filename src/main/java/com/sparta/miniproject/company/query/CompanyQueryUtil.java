package com.sparta.miniproject.company.query;

import com.querydsl.core.types.Predicate;
import com.sparta.miniproject.company.QCompany;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class CompanyQueryUtil {
    public Predicate companyNameContains(QCompany company, String keyword) {
        return StringUtils.hasText(keyword) ? company.companyName.containsIgnoreCase(keyword) : null;
    }
}

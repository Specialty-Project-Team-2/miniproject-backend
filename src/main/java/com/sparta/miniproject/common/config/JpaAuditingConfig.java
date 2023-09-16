package com.sparta.miniproject.common.config;

import com.sparta.miniproject.common.util.SecurityUtil;
import com.sparta.miniproject.member.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<Member> auditorAware() {
        return SecurityUtil::getMemberLoggedIn;
    }
}

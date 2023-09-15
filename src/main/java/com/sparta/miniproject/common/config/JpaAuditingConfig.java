package com.sparta.miniproject.common.config;

import com.sparta.miniproject.member.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<Member> auditorAware() {
        Member principal = new Member();
        principal.setId(1L);
        return () -> Optional.of(principal);
    }
}

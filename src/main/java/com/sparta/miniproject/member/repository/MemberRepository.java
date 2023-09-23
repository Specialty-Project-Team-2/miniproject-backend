package com.sparta.miniproject.member.repository;

import com.sparta.miniproject.member.dto.MemberResponseDto;
import com.sparta.miniproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByKakaoId(Long kakaoId);

}
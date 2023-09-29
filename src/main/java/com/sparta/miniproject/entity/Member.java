package com.sparta.miniproject.entity;

import com.sparta.miniproject.dto.MemberRequestDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Long kakaoId;


    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Member(String email, String password, String nickname, Long kakaoId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
    }

    public Member kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void update (MemberRequestDto memberRequestDto, PasswordEncoder passwordEncoder){
        if(!StringUtils.isBlank(memberRequestDto.getNickname())) {
            this.nickname = memberRequestDto.getNickname().strip();
        }
        if(!StringUtils.isBlank(memberRequestDto.getPassword())) {
            String password = memberRequestDto.getPassword().strip();
            this.password = passwordEncoder.encode(password);
        }
    }
}

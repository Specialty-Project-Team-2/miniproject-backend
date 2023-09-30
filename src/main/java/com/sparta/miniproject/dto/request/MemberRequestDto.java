package com.sparta.miniproject.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sparta.miniproject.entity.Member;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class MemberRequestDto {
    private final String nickname;

    @Pattern(regexp = "^[a-zA-Z_0-9`~!@#$%?]*$", message = "signup.password.not_password_form")
    private final String password;

    @JsonCreator
    public MemberRequestDto(
            String nickname,
            String password
    ) {
        this.nickname = nickname;
        this.password = password;
    }

    public void update (Member entity, PasswordEncoder passwordEncoder){
        if(!StringUtils.isBlank(this.getNickname())) {
            entity.setNickname(this.getNickname().strip());
        }
        if(!StringUtils.isBlank(this.getPassword())) {
            String password = this.getPassword().strip();
            password = passwordEncoder.encode(password);
            entity.setPassword(password);
        }
    }
}

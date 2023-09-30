package com.sparta.miniproject.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter @NoArgsConstructor
public class ServerProperties {
    @Value("${miniproject.frontend-server-url}")
    private String frontendServerUrl;
}

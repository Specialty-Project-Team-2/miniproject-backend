package com.sparta.miniproject.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.*;
import java.util.stream.Collectors;

@Slf4j(topic = "Error Caused By RestTemplate")
@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        String body = strFromInputStream(response.getBody());

        log.warn(response.getStatusCode().toString());
        log.warn(response.getHeaders().toString());
        log.warn(body);
        log.warn(response.getStatusText());
    }

    public String strFromInputStream(InputStream inputStream) throws IOException {
        try (
                Reader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            return bufferedReader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }
    }
}

package com.arty.security.commons;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SecurityCommonsAutoConfiguration {
    @Bean
    public JwtValidator jwtValidator() {
        return new JwtValidator();
    }

    @Bean
    public JwtFilter jwtFilter(JwtValidator jwtValidator) {
        return new JwtFilter(jwtValidator);
    }
}

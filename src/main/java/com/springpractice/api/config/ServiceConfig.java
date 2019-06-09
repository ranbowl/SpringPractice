package com.springpractice.api.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceConfig {

    private String clientUsername;

    private String clientPassword;

    private String clientRole;
}

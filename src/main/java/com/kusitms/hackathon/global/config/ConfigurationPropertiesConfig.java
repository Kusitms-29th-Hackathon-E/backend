package com.kusitms.hackathon.global.config;

import com.kusitms.hackathon.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class ConfigurationPropertiesConfig {
}

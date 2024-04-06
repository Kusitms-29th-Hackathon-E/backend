package com.kusitms.hackathon.global.config;

import com.kusitms.hackathon.global.client.gpt.GPTProperties;
import com.kusitms.hackathon.global.client.youtube.YoutubeProperties;
import com.kusitms.hackathon.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class, YoutubeProperties.class, GPTProperties.class})
public class ConfigurationPropertiesConfig {
}

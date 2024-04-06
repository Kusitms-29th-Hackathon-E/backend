package com.kusitms.hackathon.global.client.gpt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "gpt")
@RequiredArgsConstructor
public class GPTProperties {
    private final String apiKey;
}

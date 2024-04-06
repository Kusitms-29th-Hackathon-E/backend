package com.kusitms.hackathon.global.client.gpt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gpt")
@RequiredArgsConstructor
public class GPTProperties {
    private final String apiKey;
}

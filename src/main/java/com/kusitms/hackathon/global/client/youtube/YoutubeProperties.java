package com.kusitms.hackathon.global.client.youtube;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "youtube")
@RequiredArgsConstructor
public class YoutubeProperties {
    private final String apiKey;
}

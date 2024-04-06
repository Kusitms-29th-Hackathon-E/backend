package com.kusitms.hackathon.global.client.youtube;

import com.kusitms.hackathon.domain.mining.domain.platform.PlatformDetailResult;
import com.kusitms.hackathon.domain.mining.domain.platform.PlatformVideoInfoExtractor;
import com.kusitms.hackathon.domain.mining.domain.platform.PlatformVideoProcessingData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class YoutubePlatformVideoExtractor implements PlatformVideoInfoExtractor {
    private final YoutubeProperties youtubeProperties;

    private final WebClient webClient = WebClient.create("https://www.googleapis.com/youtube/v3/videos");


    @Override
    public PlatformDetailResult extract(PlatformVideoProcessingData platformVideoProcessingData) {
        String shortUrl = "https://youtu.be/fHlf006noFU?si=XMSsQ_b9WDJziCAb";
        Pattern pattern = Pattern.compile("youtu\\.be\\/[a-zA-Z0-9_-]{11}");
        Matcher matcher = pattern.matcher(shortUrl);

        if (matcher.find()) {
            final String videoId = matcher.group(0).split("/")[1];


            YoutubeVideoInfo youtubeVideoInfo = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("key", youtubeProperties.getApiKey())
                            .queryParam("part", "snippet")
                            .queryParam("id", videoId)
                            .build())
                    .retrieve()
                    .bodyToMono(YoutubeVideoInfo.class)
                    .block();

            return new PlatformDetailResult(youtubeVideoInfo.title(), platformVideoProcessingData.platformUrl(), youtubeVideoInfo.tags());
        }
        throw new RuntimeException("not found youtube video id");
    }

    private record YoutubeVideoInfo(
            Map<String, Object> items
    ) {
        public String title() {
            return snippets().get("title").toString();
        }

        public String thumbnailUrl() {
            return ((Map<String, Object>)((Map<String, Object>)snippets().get("thumbnails")).get("high")).get("url").toString();
        }

        public List<String> tags(){
            return (List<String>)(snippets().get("tags")==null?List.of():snippets().get("tags"));
        }


        private Map<String, Object> snippets(){
            return ((Map<String, Object>) ((Map<String, Object>) items.get(0)).get("snippets"));
        }

    }
}

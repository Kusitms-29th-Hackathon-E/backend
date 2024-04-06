package com.kusitms.hackathon.global.client.gpt;

import com.kusitms.hackathon.domain.mining.domain.question.QuestionGenerator;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionRequest;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionResult;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component

public class GPTQuestionGenerator implements QuestionGenerator {

    private final WebClient webClient = WebClient.create("https://api.openai.com/v1/chat/completions");

    private static final String PROMPT = "";

    @Override
    public QuestionResult generateQuestion(QuestionRequest questionRequest) {
return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("model", "gpt-3.5-turbo")
                        .queryParam("apiKey", "gptProperties.getApiKey()")
                        .queryParam("prompt", questionRequest.request()+" "+PROMPT)
                        .build())
                .retrieve()
                .bodyToMono(QuestionResult.class)
                .block();
    }


}

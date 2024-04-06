package com.kusitms.hackathon.global.client.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionGenerator;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionRequest;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionResult;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class GPTQuestionGenerator implements QuestionGenerator {
    private static final String PROMPT = """
            키워드와 관련된 창의적인 사고력을 기를 수 있는 질문을 ~나요?체로 생성해줘
            가능하면 사회에 비판적인 시각을 기를 수 있거나 자신의 생각을 적을 수 있는 질문이면 좋겠어
            
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘,
            한 문장으로 하나의 질문만 알려줘
            
            
            """;
    private final GPTProperties gptProperties;
    private final ObjectMapper objectMapper;
    private final WebClient webClient = WebClient.create("https://api.openai.com/v1/chat/completions");

    @Override
    @SneakyThrows
    public QuestionResult generateQuestion(QuestionRequest questionRequest) {

        var result = webClient.post()
                .header("Authorization", "Bearer " + gptProperties.getApiKey())
                .bodyValue(new GPTRequest("gpt-3.5-turbo", List.of(new Message("user", PROMPT + questionRequest.request()))))
                .retrieve()
                .bodyToMono(GPTResult.class)
                .doOnError(error -> log.info("error : {}", error))
                .block();
        return new QuestionResult(result.getContent());
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class GPTResult {
        private List<Map<String, Object>> choices;

        public String getContent() {
            return ((Map<String, Object>) choices.get(0).get("message")).get("content").toString();
        }
    }



    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class GPTRequest {
        private String model;
        private List<Message> messages;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Message {
        private String role;
        private String content;
    }


}

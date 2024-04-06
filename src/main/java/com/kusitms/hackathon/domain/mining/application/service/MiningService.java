package com.kusitms.hackathon.domain.mining.application.service;

import com.kusitms.hackathon.domain.mining.application.dto.MiningDescriptionRequest;
import com.kusitms.hackathon.domain.mining.application.dto.PlatformDetailsResponse;
import com.kusitms.hackathon.domain.mining.domain.Mining;
import com.kusitms.hackathon.domain.mining.domain.Tag;
import com.kusitms.hackathon.domain.mining.domain.platform.PlatformDetailResult;
import com.kusitms.hackathon.domain.mining.domain.platform.PlatformVideoInfoExtractor;
import com.kusitms.hackathon.domain.mining.domain.platform.PlatformVideoProcessingData;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionGenerator;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionRequest;
import com.kusitms.hackathon.domain.mining.domain.question.QuestionResult;
import com.kusitms.hackathon.domain.mining.domain.service.MiningAppender;
import com.kusitms.hackathon.domain.mining.domain.service.MiningModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MiningService {
    private final MiningAppender miningAppender;
    private final PlatformVideoInfoExtractor platformVideoInfoExtractor;
    private final QuestionGenerator questionGenerator;
    private final MiningModifier miningModifier;

    @Transactional
    public PlatformDetailsResponse createMining(String platformUrl) {
        var extract = platformVideoInfoExtractor.extract(new PlatformVideoProcessingData(platformUrl));
        if(Objects.isNull(extract.tags())){
            throw new RuntimeException("tags is empty");
        }
        final String requestQuestion = extract.tags().stream()
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElseThrow(() -> new RuntimeException("tags is empty"));
        final QuestionResult questionResult = questionGenerator.generateQuestion(new QuestionRequest(requestQuestion));
        final Mining mining = createMining(extract, questionResult);
        Long miningId = miningAppender.appendMiningAndGetId(mining);
        return new PlatformDetailsResponse(questionResult.question(), miningId);
    }

    @Transactional
    public void updateMining(MiningDescriptionRequest request, Long miningId){
        miningModifier.updateMiningResponse(miningId, request.getAnswer());
    }


    private Mining createMining(PlatformDetailResult platformDetailResult, QuestionResult questionResult) {
        return new Mining(
                null,
                LocalDate.now(),
                platformDetailResult.platformUrl(),
                platformDetailResult.title(),
                questionResult.question(),
                null,
                List.of(),
                convertDescriptionToTag(platformDetailResult.tags())
        );
    }

    private List<Tag> convertDescriptionToTag(List<String> descriptions){
        return descriptions.stream()
                .map(Tag::new)
                .toList();
    }
}

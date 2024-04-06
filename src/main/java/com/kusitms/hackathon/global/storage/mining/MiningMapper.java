package com.kusitms.hackathon.global.storage.mining;

import com.kusitms.hackathon.domain.mining.domain.ExtraMining;
import com.kusitms.hackathon.domain.mining.domain.Mining;
import com.kusitms.hackathon.domain.mining.domain.Tag;
import com.kusitms.hackathon.global.storage.mining.jpa.ExtraMiningEntity;
import com.kusitms.hackathon.global.storage.mining.jpa.MiningEntity;
import com.kusitms.hackathon.global.storage.mining.jpa.TagEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiningMapper {


    public MiningEntity toEntity(Mining mining){
        return new MiningEntity(
                mining.getId(),
                mining.getCreatedDate(),
                mining.getThumbnailUrl(),
                mining.getTitle(),
                mining.getQuestion(),
                mining.getResponse()
        );
    }

    public Mining toDomain(MiningEntity miningEntity, List<ExtraMiningEntity> extraMinings, List<TagEntity> tagEntities){
        return new Mining(
            miningEntity.getId(),
            miningEntity.getCreatedDate(),
            miningEntity.getThumbnailUrl(),
            miningEntity.getTitle(),
            miningEntity.getQuestion(),
            miningEntity.getResponse(),
            toExtraMining(extraMinings),
            toTag(tagEntities)
        );
    }

    private List<ExtraMining> toExtraMining(List<ExtraMiningEntity> extraMiningEntities){
        return extraMiningEntities.stream()
                .map(extraMiningEntity -> new ExtraMining(
                        extraMiningEntity.getCreatedDate(),
                        extraMiningEntity.getResponse()
                ))
                .toList();
    }

    private List<Tag> toTag(List<TagEntity> tagEntities){
        return tagEntities.stream()
                .map(tagEntity -> new Tag(
                        tagEntity.getDescription()
                ))
                .toList();
    }
}

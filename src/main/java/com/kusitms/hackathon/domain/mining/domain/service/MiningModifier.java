package com.kusitms.hackathon.domain.mining.domain.service;

import com.kusitms.hackathon.domain.mining.application.dto.MiningDescriptionRequest;
import com.kusitms.hackathon.domain.mining.domain.MiningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MiningModifier {
    private final MiningRepository miningRepository;

    public void updateMiningResponse(Long miningId, String response){
        miningRepository.updateMining(miningId, response);
    }
}

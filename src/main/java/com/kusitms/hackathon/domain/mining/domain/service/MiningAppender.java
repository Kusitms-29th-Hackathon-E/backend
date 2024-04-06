package com.kusitms.hackathon.domain.mining.domain.service;

import com.kusitms.hackathon.domain.mining.domain.Mining;
import com.kusitms.hackathon.domain.mining.domain.MiningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MiningAppender {
    private final MiningRepository miningRepository;

    public void appendMining(Mining mining){
        miningRepository.save(mining);
    }

}

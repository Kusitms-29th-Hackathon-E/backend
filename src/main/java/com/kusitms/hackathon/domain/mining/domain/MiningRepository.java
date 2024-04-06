package com.kusitms.hackathon.domain.mining.domain;

import com.kusitms.hackathon.domain.mining.application.service.MiningService;
import jakarta.validation.constraints.Min;

import java.util.Optional;

public interface MiningRepository {
    Mining save(Mining mining);

    void updateMining(Long miningId, String response);
}

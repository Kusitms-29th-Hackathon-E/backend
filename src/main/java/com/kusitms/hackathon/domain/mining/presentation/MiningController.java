package com.kusitms.hackathon.domain.mining.presentation;

import com.kusitms.hackathon.domain.mining.application.dto.MiningDescriptionRequest;
import com.kusitms.hackathon.domain.mining.application.dto.PlatformDetailsRequest;
import com.kusitms.hackathon.domain.mining.application.dto.PlatformDetailsResponse;
import com.kusitms.hackathon.domain.mining.application.service.MiningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MiningController {

    private final MiningService miningService;


    @PostMapping("/mining")
    public PlatformDetailsResponse appendMining(@RequestBody PlatformDetailsRequest request){
        return miningService.createMining(request.getPlatformUrl());
    }


    @PatchMapping("/mining/{miningId}")
    public void updateQuestionAnswer(
            @RequestBody MiningDescriptionRequest request,
            @PathVariable Long miningId){
        miningService.updateMining(request, miningId);
    }

}

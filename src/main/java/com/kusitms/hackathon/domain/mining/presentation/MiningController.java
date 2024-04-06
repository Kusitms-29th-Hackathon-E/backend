package com.kusitms.hackathon.domain.mining.presentation;

import com.kusitms.hackathon.domain.mining.application.dto.PlatformDetailsResponse;
import com.kusitms.hackathon.domain.mining.application.service.MiningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MiningController {

    private final MiningService miningService;


    @PostMapping("/mining")
    public PlatformDetailsResponse appendMining(@RequestBody String platformUrl){
        return miningService.createMining(platformUrl);
    }

}

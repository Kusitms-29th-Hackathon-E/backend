package com.kusitms.hackathon.domain.mining.presentation;

import com.kusitms.hackathon.domain.mining.domain.Mining;
import com.kusitms.hackathon.domain.mining.domain.MiningRepository;
import com.kusitms.hackathon.domain.mining.domain.Tag;
import com.kusitms.hackathon.domain.mining.domain.service.MiningAppender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MiningController {

    private final MiningAppender miningAppender;
    private final MiningRepository miningRepository;


    @PostMapping("/mining")
    public void appendMining(@RequestBody){

    }

}

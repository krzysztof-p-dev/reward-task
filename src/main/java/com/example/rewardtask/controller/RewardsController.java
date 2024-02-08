package com.example.rewardtask.controller;

import com.example.rewardtask.model.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class RewardsController {

    private final RewardsHandler handler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{customerId}/rewards")
    public Reward getRewards(@PathVariable("customerId") Integer customerId) {
        return handler.handleGetRewards(customerId);
    }
}

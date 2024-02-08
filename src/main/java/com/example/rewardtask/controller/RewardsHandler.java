package com.example.rewardtask.controller;

import com.example.rewardtask.entity.Customer;
import com.example.rewardtask.model.Reward;
import com.example.rewardtask.repository.CustomerRepository;
import com.example.rewardtask.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class RewardsHandler {

    private final RewardService rewardServiceImpl;
    private final CustomerRepository customerRepository;

    public Reward handleGetRewards(int customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return rewardServiceImpl.calculate(customerId);
    }
}

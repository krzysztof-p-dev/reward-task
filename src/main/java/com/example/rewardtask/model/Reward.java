package com.example.rewardtask.model;

public record Reward(int customerId, int lastMonthRewardPoints, int lastSecondMonthRewardPoints, int lastThirdMonthRewardPoints,
                     int totalRewards) {
}

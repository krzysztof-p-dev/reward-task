package com.example.rewardtask.service;

import com.example.rewardtask.convert.TransactionToTransactionDetailsConverter;
import com.example.rewardtask.filter.TransactionFilter;
import com.example.rewardtask.model.Reward;
import com.example.rewardtask.model.TransactionDetails;
import com.example.rewardtask.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.rewardtask.service.RewardData.*;

@Service
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardService {

    private static final int LAST_MONTH = 1;
    private static final int SECOND_MONTH = 2;
    private static final int THIRD_MONTH = 3;

    private final TransactionToTransactionDetailsConverter transactionToTransactionDetailsConverter;
    private final TransactionFilter transactionFilter;
    private final TransactionRepository transactionRepository;

    @Override
    public Reward calculate(int customerId) {
        List<TransactionDetails> transactionDetails = transactionRepository.findAllByCustomerId(customerId)
                .stream()
                .map(transactionToTransactionDetailsConverter::convert)
                .toList();

        int firstRewardMonth = calculateRewardForMonth(transactionDetails, LAST_MONTH);
        int secondRewardMonth = calculateRewardForMonth(transactionDetails, SECOND_MONTH);
        int thirdRewardMonth = calculateRewardForMonth(transactionDetails, THIRD_MONTH);
        int sumpPoints = firstRewardMonth + secondRewardMonth + thirdRewardMonth;

        return new Reward(customerId, firstRewardMonth, secondRewardMonth, thirdRewardMonth, sumpPoints);
    }

    private int calculateRewardForMonth(List<TransactionDetails> transactionDetails, int pastMonths) {
        var transactions = transactionFilter.filterTransactionsByPastMonths(transactionDetails, pastMonths);
        return rewardPerMonth(transactions);
    }

    private int rewardPerMonth(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .mapToInt(transaction -> calculateRewardPoints(transaction.amount()))
                .sum();
    }

    private int calculateRewardPoints(int amount) {
        int points = 0;
        if (amount > SECOND_REWARD_LIMIT) {
            points += 2 * (amount - ONE_HUNDRED_POINTS);
            points += FIFTY_POINTS;
        } else if (amount > FIRST_REWARD_LIMIT) {
            points += amount - FIFTY_POINTS;
        }
        return points;
    }
}

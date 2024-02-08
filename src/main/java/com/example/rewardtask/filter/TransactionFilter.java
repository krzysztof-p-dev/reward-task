package com.example.rewardtask.filter;

import com.example.rewardtask.model.TransactionDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionFilter {

    public List<TransactionDetails> filterTransactionsByPastMonths(List<TransactionDetails> transactions, int pastMonths) {
        LocalDate currentDate = LocalDate.now();
        LocalDate monthsAgo = currentDate.minusMonths(pastMonths);

        return transactions.stream()
                .filter(transaction -> transaction.date().isAfter(monthsAgo) && transaction.date().isBefore(currentDate))
                .collect(Collectors.toList());
    }
}

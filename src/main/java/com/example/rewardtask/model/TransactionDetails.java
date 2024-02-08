package com.example.rewardtask.model;

import java.time.LocalDate;

public record TransactionDetails(CustomerDetails customerDetails, int amount, LocalDate date) {
}

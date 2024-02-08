package com.example.rewardtask.filter;

import com.example.rewardtask.model.CustomerDetails;
import com.example.rewardtask.model.TransactionDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFilterTest {

    private final TransactionFilter transactionFilter = new TransactionFilter();

    @Test
    void shouldFilterTransactionByPastMonths() {
        // given
        CustomerDetails customerDetails = new CustomerDetails(1, "Marian"); // replace with actual constructor
        TransactionDetails transaction1 = new TransactionDetails(customerDetails, 100, LocalDate.now().minusMonths(1));
        TransactionDetails transaction2 = new TransactionDetails(customerDetails, 200, LocalDate.now().minusMonths(2));
        TransactionDetails transaction3 = new TransactionDetails(customerDetails, 300, LocalDate.now().minusMonths(6));
        List<TransactionDetails> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // when
        List<TransactionDetails> result = transactionFilter.filterTransactionsByPastMonths(transactions, 3);

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(transaction1));
        assertTrue(result.contains(transaction2));
        assertFalse(result.contains(transaction3));
    }
}
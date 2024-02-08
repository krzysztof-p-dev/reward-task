package com.example.rewardtask.service;

import com.example.rewardtask.convert.TransactionToTransactionDetailsConverter;
import com.example.rewardtask.entity.Transaction;
import com.example.rewardtask.filter.TransactionFilter;
import com.example.rewardtask.model.CustomerDetails;
import com.example.rewardtask.model.Reward;
import com.example.rewardtask.model.TransactionDetails;
import com.example.rewardtask.repository.TransactionRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RewardsServiceImplTest {

    private static final int CUSTOMER_ID = 3;
    private static final String CUSTOMER_NAME = "Tom Mark";
    private static final int AMOUNT = 100;
    private static final LocalDate DATE = LocalDate.of(2024,2,1);

    @Autowired
    private RewardsServiceImpl rewardsService;

    @Mock
    private TransactionToTransactionDetailsConverter transactionToTransactionDetailsConverter;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        CustomerDetails customerDetails = new CustomerDetails(CUSTOMER_ID, CUSTOMER_NAME);
        transaction = new Transaction();
        TransactionDetails transactionDetails = new TransactionDetails(customerDetails, AMOUNT, DATE);
        when(transactionToTransactionDetailsConverter.convert(transaction)).thenReturn(transactionDetails);
    }

    @Test
    public void shouldReturnCorrectRewardValue () {
        //given
        when(transactionRepository.findAllByCustomerId(CUSTOMER_ID)).thenReturn(Collections.singletonList(transaction));

        //when
        Reward reward = rewardsService.calculate(CUSTOMER_ID);

        //then
        assertNotNull(reward);
        assertEquals(CUSTOMER_ID,reward.customerId());
        assertEquals(50,reward.lastMonthRewardPoints());
        assertEquals(50,reward.lastSecondMonthRewardPoints());
        assertEquals(50,reward.lastThirdMonthRewardPoints());
        assertEquals(150,reward.totalRewards());
    }
}
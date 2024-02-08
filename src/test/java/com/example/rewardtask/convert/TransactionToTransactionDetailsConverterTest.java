package com.example.rewardtask.convert;

import com.example.rewardtask.entity.Customer;
import com.example.rewardtask.entity.Transaction;
import com.example.rewardtask.model.CustomerDetails;
import com.example.rewardtask.model.TransactionDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionToTransactionDetailsConverterTest {

    @Autowired
    private TransactionToTransactionDetailsConverter converter;
    @Mock
    private CustomerToCustomerDetailsConverter customerToCustomerDetailsConverter;

    private Transaction transaction;
    private Customer customer;
    private CustomerDetails customerDetails;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setName("Test Customer");

        customerDetails = new CustomerDetails(1, "Test Customer");

        transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAmount(100);
        transaction.setTransactionDate(LocalDate.now());
    }

    @Test
    void shouldConvertTransactionToTransactionDetails() {
        //when
        when(customerToCustomerDetailsConverter.convert(customer)).thenReturn(customerDetails);
        TransactionDetails details = converter.convert(transaction);

        //then
        assertNotNull(details);
        assertEquals(customerDetails, details.customerDetails());
        assertEquals(transaction.getAmount(), details.amount());
        assertEquals(transaction.getTransactionDate(), details.date());
    }
}
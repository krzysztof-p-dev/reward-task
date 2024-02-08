package com.example.rewardtask.convert;

import com.example.rewardtask.entity.Transaction;
import com.example.rewardtask.model.CustomerDetails;
import com.example.rewardtask.model.TransactionDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransactionToTransactionDetailsConverter implements Converter<Transaction, TransactionDetails> {

    private final CustomerToCustomerDetailsConverter customerToCustomerDetailsConverter;

    @Override
    public TransactionDetails convert(Transaction transaction) {
        CustomerDetails customerDetails = customerToCustomerDetailsConverter.convert(transaction.getCustomer());
        return new TransactionDetails(customerDetails, transaction.getAmount(), transaction.getTransactionDate());
    }
}

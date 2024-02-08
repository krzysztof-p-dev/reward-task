package com.example.rewardtask.convert;

import com.example.rewardtask.entity.Customer;
import com.example.rewardtask.model.CustomerDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDetailsConverter implements Converter<Customer, CustomerDetails> {

    @Override
    public CustomerDetails convert(Customer customer) {
        return new CustomerDetails(customer.getId(), customer.getName());
    }
}

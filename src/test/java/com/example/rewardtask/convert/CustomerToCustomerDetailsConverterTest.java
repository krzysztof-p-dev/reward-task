package com.example.rewardtask.convert;

import com.example.rewardtask.entity.Customer;
import com.example.rewardtask.entity.Transaction;
import com.example.rewardtask.model.CustomerDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerToCustomerDetailsConverterTest {

    @Autowired
    private CustomerToCustomerDetailsConverter converter;

    @Test
    void shouldConvertCustomerToCustomerDetails() {
        //given
        int id = 1;
        String customerName = "Mirek";
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(customerName);

        //when
        CustomerDetails customerDetails = converter.convert(customer);

        //then
        assertNotNull(customerDetails);
        assertEquals(customerDetails.name(), customerName);
        assertEquals(customerDetails.id(), id);
    }
}
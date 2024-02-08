package com.example.rewardtask.repository;

import com.example.rewardtask.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    Customer findCustomerById(Integer customerId);
}

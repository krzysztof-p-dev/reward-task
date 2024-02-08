package com.example.rewardtask.repository;

import com.example.rewardtask.entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

    List<Transaction> findAllByCustomerId(Integer customerId);

}

package com.aks.OrderItems.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aks.OrderItems.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

}

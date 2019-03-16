package com.aks.OrderItems.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aks.OrderItems.model.Account;
import com.aks.OrderItems.model.Orders;

public interface OrdersRepository extends MongoRepository<Orders, String>{

}

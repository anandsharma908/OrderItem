package com.aks.OrderItems.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aks.OrderItems.model.Account;
import com.aks.OrderItems.model.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String>{

}

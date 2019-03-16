package com.aks.OrderItems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import com.aks.OrderItems.model.Account;
import com.aks.OrderItems.model.Inventory;
import com.aks.OrderItems.model.Orders;
import com.aks.OrderItems.repository.AccountRepository;
import com.aks.OrderItems.repository.InventoryRepository;
import com.aks.OrderItems.repository.OrdersRepository;

@RestController
@RequestMapping("/order")
public class OrdersController {
	
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	InventoryController inventoryController;
	
	
	
	@GetMapping("/all")
	public List<Orders> getAllUsers(){
		return ordersRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Orders orders) {
		
		Inventory inventory=inventoryRepository.findOne(orders.getInventoryId());
		int qty=inventory.getQty();
		if(orders.getQty()>qty) {
			return "inventory not available";
		}else {
			inventory.setQty(qty-orders.getQty());
		}
		inventoryController.update(inventory);
		ordersRepository.save(orders);
		return "order processed";
    }

	@RequestMapping(value = "/{id}") 
    public Orders read(@PathVariable String id) {
        return ordersRepository.findOne(id);
    }
	
	  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String update(@RequestBody Orders order) {
		  
		  Inventory inventory=inventoryRepository.findOne(order.getInventoryId());
		  //Orders tempOrder=ordersRepository.findOne(order.getId());
			int qty=inventory.getQty();
			if(order!=null) {
				
				if(order.getQty()>qty) {
					return "inventory limit exceeded";
				}
				else if(order.getQty()<qty){
					inventory.setQty(qty+order.getQty());
				}
				else {
					inventory.setQty(qty-order.getQty());
				}
				inventoryController.update(inventory);
				ordersRepository.save(order);
				return "update process done";
			}
			else {
				return "invalid order";
			}
	    }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	    public void delete(@PathVariable String id) {
		  ordersRepository.delete(id); 
	    }
}

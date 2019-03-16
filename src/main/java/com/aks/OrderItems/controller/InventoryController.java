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
import com.aks.OrderItems.repository.AccountRepository;
import com.aks.OrderItems.repository.InventoryRepository;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@GetMapping("/all")
	public List<Inventory> getAllUsers(){
		return inventoryRepository.findAll();
	}

	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Inventory create(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
    }

    @RequestMapping(value = "/{id}") 
    public Inventory read(@PathVariable String id) {
        return inventoryRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Inventory inventory) {
    	inventoryRepository.save(inventory);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	inventoryRepository.delete(id); 
    }

}

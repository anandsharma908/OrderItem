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
import com.aks.OrderItems.repository.AccountRepository;

@RestController
@RequestMapping("/user")
public class AccountsController {
	
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/all")
	public List<Account> getAllUsers(){
		return accountRepository.findAll();
	}

	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account create(@RequestBody Account account) {
		return accountRepository.save(account);
    }

    @RequestMapping(value = "/{id}") 
    public Account read(@PathVariable String id) {
        return accountRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Account user) {
    	accountRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	accountRepository.delete(id); 
    }

}

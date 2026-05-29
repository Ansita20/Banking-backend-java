package com.example.banking_web.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_web.dto.AccountDto;
import com.example.banking_web.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(
	        @PathVariable Long id,
	        @RequestBody Map<String, Double> request) {

	    Double amount = request.get("amount");

	    AccountDto accountDto = accountService.deposit(id, amount);

	    return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(
	        @PathVariable Long id,
	        @RequestBody Map<String, Double> request) {

	    Double amount = request.get("amount");

	    AccountDto accountDto = accountService.deposit(id, amount);

	    return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccount();
		return ResponseEntity.ok(accounts);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted successfully!");
	}
	
	@PutMapping("/{id1}/{id2}/transfer")
	public ResponseEntity<AccountDto> transfer(
			@PathVariable Long id1, Long id2,
			@RequestBody Map<String, Double> request
			){
		Double amount = request.get("amount");

	    AccountDto accountDto = accountService.deposit(id1, amount);

	    return ResponseEntity.ok(accountDto);
	}
}

package com.example.banking_web.service;

import java.util.List;

import com.example.banking_web.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
    
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List <AccountDto> getAllAccount();
	
	void deleteAccount(Long id);
	
	AccountDto transfer(Long id1, Long id2, double amount);
}
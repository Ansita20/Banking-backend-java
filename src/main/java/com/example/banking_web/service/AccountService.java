package com.example.banking_web.service;

import com.example.banking_web.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
    
	AccountDto deposit(Long id, double amount);
}
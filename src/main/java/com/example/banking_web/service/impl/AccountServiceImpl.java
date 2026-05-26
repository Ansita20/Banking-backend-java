package com.example.banking_web.service.impl;

import org.springframework.stereotype.Service;

import com.example.banking_web.dto.AccountDto;
import com.example.banking_web.entity.account;
import com.example.banking_web.mapper.AccountMapper;
import com.example.banking_web.repository.AccountRepository;
import com.example.banking_web.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		account Account = AccountMapper.maptoAccount(accountDto);
		account savedAccount = accountRepository.save(Account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		account Account = accountRepository
			.findById(id)
			.orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(Account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {

	    account Account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));

	    double total = Account.getBalance() + amount;
	    Account.setBalance(total);

	    account updatedAccount = accountRepository.save(Account);

	    return AccountMapper.mapToAccountDto(updatedAccount);
	}

}

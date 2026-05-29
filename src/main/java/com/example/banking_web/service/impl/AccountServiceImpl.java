package com.example.banking_web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public AccountDto withdraw(Long id, double amount) {

	    account Account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));

	    if(Account.getBalance() < amount) {
	    	throw new RuntimeException("Insufficient amount");
	    }
	    
	    double total = Account.getBalance() - amount;
	    Account.setBalance(total);

	    account updatedAccount = accountRepository.save(Account);

	    return AccountMapper.mapToAccountDto(updatedAccount);
	}
	
	@Override
	public List<AccountDto> getAllAccount() {
		List<account> Accounts = accountRepository.findAll();
		return Accounts.stream().map((Account) -> AccountMapper.mapToAccountDto(Account))
			.collect(Collectors.toList());
	}
	
	@Override
	public void deleteAccount(Long id) {
		account Account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists!"));
		accountRepository.deleteById(id);
	}
	
	public AccountDto transfer(Long id1, Long id2, double amount) {
		account Account1 = accountRepository
	            .findById(id1)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));
		account Account2 = accountRepository
	            .findById(id2)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));

		double total1 = Account1.getBalance() - amount;
		double total2 = Account2.getBalance() + amount;
		Account1.setBalance(total1);
		Account2.setBalance(total2);
		
		accountRepository.save(Account1);
		accountRepository.save(Account2);

	    return AccountMapper.mapToAccountDto(Account1);
	}
}

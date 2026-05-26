package com.example.banking_web.mapper;

import com.example.banking_web.dto.AccountDto;
import com.example.banking_web.entity.account;

public class AccountMapper {
	public static account maptoAccount(AccountDto dto) {

	    account acc = new account();
	    acc.setAccountHolderName(dto.getAccountHolderName());
	    acc.setBalance(dto.getBalance());

	    return acc;
	}
	
	public static AccountDto mapToAccountDto(account Account) {
		AccountDto accountDto = new AccountDto(
			    Account.getId(),
			    Account.getAccountHolderName(),
			    Account.getBalance()
			);
				
		return accountDto;
	}
}

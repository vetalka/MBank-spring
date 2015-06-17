package com.mbank.service;

import java.util.List;

import com.mbank.model.Accounts;

public interface AccountService {

	void addClient(Accounts accounts);
	
	Accounts ViewAccountDetails(Long accountId);

	List<Accounts> getAllAccounts();
	
}

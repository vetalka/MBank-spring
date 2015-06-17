package com.mbank.service;

import java.util.List;

import com.mbank.model.DepositType;
import com.mbank.model.Deposits;

public interface DepositsService {
	
	Deposits getDeposit(Long depositId);
	
	List<Deposits> getAllDeposits();
	
	void crateNewDeposit(Deposits deposits , long clientId);
	
	List<DepositType> findAllDepositType();
	
	void preOpenDeposite(long clientId , long depositId);

}

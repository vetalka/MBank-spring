package com.mbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbank.model.Accounts;
import com.mbank.model.Properties;
import com.mbank.repository.AccountRepository;
import com.mbank.repository.ActivityRepository;
import com.mbank.repository.PropertiesRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private PropertiesRepository propertiesRepository;

	private String regularCreditLimit = null ;
	private String goldCreditLimit = null ;
	private String platinumCreditLimit = null ;
	
	public void addClient(Accounts accounts) {
		
		List<Properties> prop = propertiesRepository.findAll();
		for (Properties properties : prop) {
			if(properties.getPropKey().equals("regular_credit_limit")){
				 regularCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_credit_limit")){
				 goldCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_credit_limit")){
			      platinumCreditLimit = properties.getPropValue();
			}
		}
		
		
		accounts.setComment("Frirst Deposit Create Client");
		
        if (accounts.getBalance() <= 10000 ){
			
			accounts.setCreditLimit(regularCreditLimit);
			
		}else if (accounts.getBalance() <= 100000 && accounts.getBalance() > 10000){
			
			accounts.setCreditLimit(goldCreditLimit);
			
		}else if(accounts.getBalance() > 100000){
			
			accounts.setCreditLimit(platinumCreditLimit);
			
		}
        
        accounts.setBalance(accounts.getBalance()-0.5);
        
		accountRepository.save(accounts);
		
		
		
		
	}

	@Transactional
	public Accounts ViewAccountDetails(Long accountId) {
		
		return accountRepository.findOne(accountId);
	}

	@Transactional
	public List<Accounts> getAllAccounts() {
		return accountRepository.findAll();
	}
	


	
}

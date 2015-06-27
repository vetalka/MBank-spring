package com.mbank.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbank.model.Accounts;
import com.mbank.model.Activity;
import com.mbank.model.ClientType;
import com.mbank.model.Clients;
import com.mbank.model.Deposits;
import com.mbank.model.Properties;
import com.mbank.repository.AccountRepository;
import com.mbank.repository.ActivityRepository;
import com.mbank.repository.AuthorityRepository;
import com.mbank.repository.ClientRepository;
import com.mbank.repository.DepositsRepository;
import com.mbank.repository.PropertiesRepository;
import com.mbank.repository.UsersRepository;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private DepositsRepository depositsRepository;
	
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private AuthorityRepository authoritiesRepository;
	
	
	private String regularCreditLimit = null ;
	private String goldCreditLimit = null ;
	private String platinumCreditLimit = null ;
	private String regularDailyInterest = null;
	private String regularDepositeCommission = null;
	private String goldDailyInterest = null ;
	private String goldDepositeCommission = null;
	private String platinumDailyInterest = null;
	private String platinumDepositeCommission = null;
	private String preOpenFee = null;
	
	@Transactional
	public void addClient(Clients clients) {
	
		clientRepository.save(clients);

	}

	@Transactional
	public List<ClientType> findAllClientType() {
		
        List<ClientType> clientType = new ArrayList<ClientType>();
		
		ClientType regular = new ClientType();
		regular.setDesc("REGULAR");
		clientType.add(regular);
		
		ClientType gold = new ClientType();
		gold.setDesc("GOLD");
		clientType.add(gold);
		
		ClientType platinum = new ClientType();
		platinum.setDesc("PLATINUM");
		clientType.add(platinum);
		
		return clientType;
	}

	@Transactional
	public void removeClient(Long clientId) {
		
		List<Properties> prop = propertiesRepository.findAll();
		for (Properties properties : prop) {
			if(properties.getPropKey().equals("regular_daily_interest")){
				regularDailyInterest=properties.getPropValue();
			}else if(properties.getPropKey().equals("regular_deposit_commission")){
				regularDepositeCommission=properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_daily_interest")){
				goldDailyInterest = properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_deposit_commission")){
				goldDepositeCommission=properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_daily_interest")){
				platinumDailyInterest = properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_deposit_commission")){
				platinumDepositeCommission = properties.getPropValue();
			}else if(properties.getPropKey().equals("regular_credit_limit")){
				 regularCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_credit_limit")){
				 goldCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_credit_limit")){
			      platinumCreditLimit = properties.getPropValue();
			}else if (properties.getPropKey().equals("pre_open_fee")){
				preOpenFee = properties.getPropValue();
			}
		}	

        List<Deposits> deposit = depositsRepository.findAll();
		
		for( Deposits depositsBean : deposit){
			
			
		//	Map <String , String> prop = dbManagers.getProperties();

	    	Accounts accountBean = accountRepository.findOne(clientId);
	    	
	    	Clients clientBean = clientRepository.findOne(clientId);
	    	
	    	Calendar closingDate = Calendar.getInstance();
			closingDate.setTimeInMillis(depositsBean.getClosingDate().getTime());
	    	
			
			Calendar openingDate = Calendar.getInstance();
		    Date o = new Date(openingDate.getTimeInMillis());
			depositsBean.setOpeningDate(o);
			openingDate.setTimeInMillis(depositsBean.getOpeningDate().getTime());
			
			Activity activityBean = new Activity();
			//not nedd activity id
			activityBean.setAmount(depositsBean.getBalance());
		
				 if(clientBean.getClientType().equals("REGULAR")){			
									
						activityBean.setCommission(Double.parseDouble(regularDailyInterest));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(regularDepositeCommission));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));
							    	
					    	depositsBean.setDepositType("LongDeposit");
					    	activityBean.setDescription("regular_deposite_commission" + "%");
							activityBean.setCommission(Double.parseDouble(regularDepositeCommission));
					    
					}else if(clientBean.getClientType().equals("GOLD")){
						
						activityBean.setCommission(Double.parseDouble(goldDailyInterest));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(goldDepositeCommission));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

					    	    depositsBean.setDepositType("LongDeposit");
					    	    activityBean.setDescription("gold_deposite_commission" + "%");
					    	    activityBean.setCommission(Double.parseDouble(goldDepositeCommission));
					    	    
					}else if(clientBean.getClientType().equals("PLATINUM")){
						
						activityBean.setCommission(Double.parseDouble(platinumDailyInterest));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(0.21)*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(platinumDepositeCommission));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

					    	        depositsBean.setDepositType("LongDeposit");
					    	        activityBean.setDescription("platinum_deposite_commission" + "%");
					    			activityBean.setCommission(Double.parseDouble(platinumDepositeCommission));
					    			
					}    
			 
			 
			 activityBean.setCommission(Double.parseDouble(preOpenFee));
			 activityBean.setDescription("pre_open_fee");
			 
			 accountBean.setBalance(accountBean.getBalance()+(depositsBean.getEstimatedBalance())-((depositsBean.getEstimatedBalance())*(activityBean.getCommission())));
	         accountBean.setComment("pre_open_fee");
			 activityBean.setActivityDate(o);
			 activityBean.setAmount((depositsBean.getEstimatedBalance())-((depositsBean.getEstimatedBalance())*(activityBean.getCommission())));
	         
	         
	         if (accountBean.getBalance() <= 10000 ){
	  			
	  			accountBean.setCreditLimit(regularCreditLimit);
	  			clientBean.setClientType("REGULAR");
	  			
	  		}else if (accountBean.getBalance() <= 100000 && accountBean.getBalance() > 10000){
	  			
	  			accountBean.setCreditLimit(goldCreditLimit);
	  			clientBean.setClientType("GOLD");
	  			
	  		}else if(accountBean.getBalance() > 100000){
	  			
	  			accountBean.setCreditLimit(platinumCreditLimit);
	  			clientBean.setClientType("PLATINUM");
	  			
	  		}
			
	      
	         depositsRepository.delete(depositsBean);
		}

		clientRepository.delete(clientId);
		accountRepository.delete(clientId);
		activityRepository.delete(clientId);
		authoritiesRepository.delete(clientId);
		usersRepository.delete(clientId);
		
	}


	@Transactional
	public void updateClientAdmin(Long clientId, String clientType,
			String comment) {
		
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
		
		clientRepository.updateClientAdmin(clientId, clientType, comment);
		
		Clients client = clientRepository.findOne(clientId);

		Accounts accounts = accountRepository.findOne(clientId);
		
		if(client.getClientType().equals("REGULAR")){

			accounts.setCreditLimit(regularCreditLimit);

		}else if(client.getClientType().equals("GOLD")){
			
			accounts.setCreditLimit(goldCreditLimit);
				
		}else if (client.getClientType().equals("PLATINUM")){
			
			accounts.setCreditLimit(platinumCreditLimit);
			
		}
		
		accountRepository.updateBalanceAndCreditLimit(clientId, accounts.getBalance(), accounts.getCreditLimit());
	}


	@Transactional
	public List<Clients> getAllClients() {
		
		return clientRepository.findAll();
	}

	@Transactional
	public Clients viewClientDetails(Long clientId) {
		
		return clientRepository.findOne(clientId);
		
	}

	@Transactional
	public void updateClient(long clientId, String Address, String Email,
			String Phone) {

		clientRepository.updateClient(clientId, Address, Email, Phone);
	}

	
}

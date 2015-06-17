package com.mbank.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbank.model.Accounts;
import com.mbank.model.Activity;
import com.mbank.model.Authorities;
import com.mbank.model.Clients;
import com.mbank.model.Properties;
import com.mbank.model.Users;
import com.mbank.repository.AccountRepository;
import com.mbank.repository.ActivityRepository;
import com.mbank.repository.AuthorityRepository;
import com.mbank.repository.ClientRepository;
import com.mbank.repository.PropertiesRepository;
import com.mbank.repository.UsersRepository;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private AuthorityRepository authoritiesRepository;
	
	private String regularCreditLimit = null ;
	private String goldCreditLimit = null ;
	private String platinumCreditLimit = null ;
	private String commissionRate = null;
	
	@Transactional
	public void addActivity(Activity activity) {

		
		Calendar acitityDate = Calendar.getInstance();
	    Date c = new Date(acitityDate.getTimeInMillis());
	    
		activity.setActivityDate(c);
		activity.setDescription("Frirst Deposit Create Client");
		activity.setCommission(0.5);

		activityRepository.save(activity);
		
		Accounts account = accountRepository.findOne(activity.getActivityId());
		
		activity.setClientId(activity.getActivityId());
		activity.setAmount(account.getBalance());
		
		activityRepository.updateActivity(activity.getActivityId(), activity.getActivityId(), activity.getAmount());
			
		Clients clients = clientRepository.findOne(activity.getActivityId());
		
		    Users user = new Users();
	        Authorities authorities = new Authorities();
	        
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(clients.getPassword());
			user.setPassword(hashedPassword);
			
			user.setUsername(clients.getClientName());
			user.setEnabled(true);
			user.setClientId(clients.getClientId());
			authorities.setAuthority("ROLE_USER");
			authorities.setClientName(clients.getClientName());
			
			usersRepository.save(user);
			authoritiesRepository.save(authorities);
			
			Accounts accounts = accountRepository.findOne(activity.getActivityId());
			
			accounts.setClientId(accounts.getAccountId());
			accountRepository.update(accounts.getAccountId(), accounts.getAccountId());
			

		
	}

	@Transactional
	public Activity viewActivitiesDetails(Long activityId) {
		
		return activityRepository.findOne(activityId);
	}

	@Transactional
	public List<Activity> getAllActivities() {
		
		return activityRepository.findAll();
	}

	@Transactional
	public void depositToAccount(long clientId, double amount) {

		List<Properties> prop = propertiesRepository.findAll();
		for (Properties properties : prop) {
			if(properties.getPropKey().equals("regular_credit_limit")){
				 regularCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_credit_limit")){
				 goldCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_credit_limit")){
			      platinumCreditLimit = properties.getPropValue();
			}else if (properties.getPropKey().equals("commission_rate")){
				commissionRate = properties.getPropValue();
			}
		}
		
		Calendar openingDate = Calendar.getInstance();
		Date o = new Date(openingDate.getTimeInMillis());
		
		Accounts account = accountRepository.findOne(clientId);
		Clients client = clientRepository.findOne(clientId);
		Activity activity = new Activity();
		
		account.setAccountId(clientId);
		
		activity.setClientId(clientId);
		activity.setAmount(amount);
		activity.setActivityDate(o);
    	activity.setCommission(Double.parseDouble(commissionRate));
    	activity.setDescription("Deposit");
    	account.setComment("Deposit");
    	account.setBalance(account.getBalance()-activity.getCommission()+activity.getAmount());
    	
        if (account.getBalance() <= 10000 ){
			
			account.setCreditLimit(regularCreditLimit);
			client.setClientType("REGULAR");
			
		}else if (account.getBalance() <= 100000 && account.getBalance() > 10000){
			
			account.setCreditLimit(goldCreditLimit);
			client.setClientType("GOLD");
			
		}else if(account.getBalance() > 100000){
			
			account.setCreditLimit(platinumCreditLimit);
			client.setClientType("PLATINUM");
			
		}

       client.setComment("deposit");
    	
       activityRepository.save(activity);
       accountRepository.updateBalanceAndCreditLimit(account.getAccountId(), account.getBalance(), account.getCreditLimit());
       clientRepository.updateClientAdmin(clientId, client.getClientType(), client.getComment());

	}

	@Transactional
	public void withdrawFromAccount(long clientId, double amount) {
		
		List<Properties> prop = propertiesRepository.findAll();
		for (Properties properties : prop) {
			if(properties.getPropKey().equals("regular_credit_limit")){
				 regularCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("gold_credit_limit")){
				 goldCreditLimit=properties.getPropValue();
			}else if(properties.getPropKey().equals("platinum_credit_limit")){
			      platinumCreditLimit = properties.getPropValue();
			}else if (properties.getPropKey().equals("commission_rate")){
				commissionRate = properties.getPropValue();
			}
		}
		
        Accounts account = accountRepository.findOne(clientId);
    	
    	Calendar openingDate = Calendar.getInstance();
		java.sql.Date o = new Date(openingDate.getTimeInMillis());
		
		account.setAccountId(clientId);
		
    	Activity activity = new Activity();
    	Clients client = clientRepository.findOne(clientId);
    	
    	activity.setActivityDate(o);
    	activity.setCommission(Double.parseDouble(commissionRate));
    	activity.setDescription("Withdraw");
    	account.setComment("Withdraw");
   	    client.setComment("Withdraw");

    	activity.setClientId(clientId);
    	activity.setAmount(amount);
	
    	if((account.getBalance()+(Double.parseDouble(account.getCreditLimit()))) < amount){
    		
         	try {
				throw new Exception("You do not have enough money in the account! Please try again !");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	
        }else {
        	
    		account.setBalance(account.getBalance()-activity.getCommission()-activity.getAmount());
        }
    	 if (account.getBalance() <= 10000 ){
 			
 			account.setCreditLimit(regularCreditLimit);
 			client.setClientType("REGULAR");
 			
 		}else if (account.getBalance() <= 100000 && account.getBalance() > 10000){
 			
 			account.setCreditLimit(goldCreditLimit);
 			client.setClientType("GOLD");
 			
 		}else if(account.getBalance() > 100000){
 			
 			account.setCreditLimit(platinumCreditLimit);
 			client.setClientType("PLATINUM");
 			
 		}
    	 
    	 activityRepository.save(activity);
    	 accountRepository.updateBalanceAndCreditLimit(clientId, account.getBalance(), account.getCreditLimit());
         clientRepository.updateClientAdmin(clientId, client.getClientType(), client.getComment());

     	 
    	 }
   }



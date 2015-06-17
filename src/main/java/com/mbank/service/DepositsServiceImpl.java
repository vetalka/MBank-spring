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
import com.mbank.model.Clients;
import com.mbank.model.DepositType;
import com.mbank.model.Deposits;
import com.mbank.model.Properties;
import com.mbank.repository.AccountRepository;
import com.mbank.repository.ActivityRepository;
import com.mbank.repository.ClientRepository;
import com.mbank.repository.DepositsRepository;
import com.mbank.repository.PropertiesRepository;

@Service("depositsService")
public class DepositsServiceImpl implements DepositsService {

	
	@Autowired
	private DepositsRepository depositsRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private PropertiesRepository propertiesRepository;

	
	private String regularDailyInterest = null;
	private String regularDepositeCommission = null;
	private String goldDailyInterest = null ;
	private String goldDepositeCommission = null;
	private String platinumDailyInterest = null;
	private String platinumDepositeCommission = null;
	private String regularCreditLimit = null ;
	private String goldCreditLimit = null ;
	private String platinumCreditLimit = null ;
	private String preOpenFee = null;
	
	@Transactional
	public Deposits getDeposit(Long depositId) {
		
		return depositsRepository.findOne(depositId);
	}

	@Transactional
	public List<Deposits> getAllDeposits() {
		
		return depositsRepository.findAll();
		
	}
	
	@Transactional
	public List<DepositType> findAllDepositType() {
		
        List<DepositType> depositType = new ArrayList<DepositType>();
		
		DepositType shortDeposit = new DepositType();
		shortDeposit.setDesc("ShortDeposit");
		depositType.add(shortDeposit);
		
		DepositType longDeposit = new DepositType();
		longDeposit.setDesc("LongDeposit");
		depositType.add(longDeposit);
		
		return depositType;
	}

	@Transactional
	public void crateNewDeposit(Deposits deposits , long clientId) {
		
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
				
		Calendar openingDate = Calendar.getInstance();
		openingDate.setTimeInMillis(deposits.getOpeningDate().getTime());
		Calendar closingDate = Calendar.getInstance();
		closingDate.setTimeInMillis(deposits.getClosingDate().getTime());
 
		deposits.setClientId(clientId);
		deposits.getBalance();
		deposits.setDepositType("aaa");
		deposits.setEstimatedBalance(1);

		depositsRepository.save(deposits);
		depositsRepository.findOne(deposits.getDepositId());
		
		Clients client = clientRepository.findOne(clientId);
		
		Activity activityBean = new Activity();
		
		activityBean.setClientId(clientId);
		activityBean.setAmount(deposits.getBalance());
		
		if(client.getClientType().equals("REGULAR")){			
			
			activityBean.setCommission(Double.parseDouble(regularDailyInterest));
			deposits.setEstimatedBalance((deposits.getBalance())+deposits.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
			
		    if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	deposits.setDepositType("LongDeposit");
		    	activityBean.setDescription("regular_deposite_commission" + "%");
				activityBean.setCommission(Double.parseDouble(regularDepositeCommission));
			
			}else {
				
				deposits.setDepositType("ShortDeposit");
				activityBean.setDescription("regular_deposite_commission" + "%");
				activityBean.setCommission(Double.parseDouble(regularDepositeCommission));
				
			}
		    
		}else if(client.getClientType().equals("GOLD")){
			
			activityBean.setCommission(Double.parseDouble(goldDailyInterest));
			deposits.setEstimatedBalance((deposits.getBalance())+deposits.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));

              if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	    deposits.setDepositType("LongDeposit");
		    	    activityBean.setDescription("gold_deposite_commission" + "%");
		    	    activityBean.setCommission(Double.parseDouble(goldDepositeCommission));
		    	    
			  }else {
				
				  deposits.setDepositType("ShortDeposit");
				  activityBean.setDescription("gold_deposite_commission" + "%");
				  activityBean.setCommission(Double.parseDouble(goldDepositeCommission));				
			
		      } 
              
		}else if(client.getClientType().equals("PLATINUM")){
			
			activityBean.setCommission(Double.parseDouble(platinumDailyInterest));
			deposits.setEstimatedBalance((deposits.getBalance())+deposits.getBalance()*(0.21)*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));

                   if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	        deposits.setDepositType("LongDeposit");
		    	        activityBean.setDescription("platinum_deposite_commission" + "%");
		    			activityBean.setCommission(Double.parseDouble(platinumDepositeCommission));
			
		       	   }else {
				
				        deposits.setDepositType("ShortDeposit");
				        activityBean.setDescription("platinum_deposite_commission" + "%");
						activityBean.setCommission(Double.parseDouble(platinumDepositeCommission));
				
			       }
		       }    
		
		activityBean.setActivityDate(deposits.getOpeningDate());
		activityBean.setClientId(deposits.getClientId());
		
		activityRepository.save(activityBean);

		depositsRepository.updateDeposit(deposits.getClientId(), deposits.getBalance(), deposits.getDepositType(), deposits.getEstimatedBalance(), deposits.getOpeningDate(), deposits.getClosingDate());
	}

	@Transactional
	public void preOpenDeposite(long clientId, long depositId) {
		
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

        Accounts account = accountRepository.findOne(clientId);
    	
    	Clients client = clientRepository.findOne(clientId);
    	
    	Deposits deposit = depositsRepository.findOne(depositId); 
    	
    	Calendar today = Calendar.getInstance();
	    Date c = new Date(today.getTimeInMillis());
    	
    	Calendar closingDate = Calendar.getInstance();
		closingDate.setTimeInMillis(deposit.getClosingDate().getTime());
    	
		
		Calendar openingDate = Calendar.getInstance();
		Date open = new Date(openingDate.getTimeInMillis());
		deposit.setOpeningDate(open);
		openingDate.setTimeInMillis(deposit.getOpeningDate().getTime());
		
		Activity activity = new Activity();
		
		if(deposit.getDepositType().equals("ShortDeposit")){
				//need to get exsepsion	 
					 
					 try {
						throw new Exception("You cant do it!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					 
				 }else if(deposit.getDepositType().equals("LongDeposit")){
					 
					   
				 activity.setCommission(Double.parseDouble(preOpenFee));
				 activity.setDescription("pre_open_fee");
				 
				 account.setBalance(account.getBalance()+(deposit.getBalance())+(deposit.getBalance())*(activity.getCommission())*((today.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
		         account.setComment("pre_open_fee");
				 activity.setActivityDate(open);
				 activity.setAmount((deposit.getBalance())-((deposit.getBalance())*(activity.getCommission())*((today.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)))));
		         
		         depositsRepository.delete(depositId);
		         activity.setClientId(clientId);
		         activityRepository.save(activity);
				 
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
		          
		         accountRepository.updateBalanceAndCreditLimit(clientId, account.getBalance(), account.getCreditLimit());
		         clientRepository.updateClientAdmin(clientId, client.getClientType(), client.getComment());
			
				 }
			
	}


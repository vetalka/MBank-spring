package com.mbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbank.model.Accounts;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Accounts , Long> {
	
	
//	@Query(UPDATE Accounts a SET a.clientId WHERE a.accountId= :accountId)
//	public void update(Accounts account);
	
	@Modifying
	@Query("UPDATE Accounts a SET a.clientId = ?2 WHERE a.accountId = ?1")
	public void update( long accountId , long clientId);
	
	@Modifying
	@Query("UPDATE Accounts a SET a.balance = ?2 , a.creditLimit = ?3 WHERE a.accountId = ?1")
	public void updateBalanceAndCreditLimit( long accountId , double balance , String creditLimit);
}

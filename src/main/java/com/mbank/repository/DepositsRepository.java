package com.mbank.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbank.model.Deposits;

@Repository("depositsRepository")
public interface DepositsRepository extends JpaRepository<Deposits , Long>{

	@Modifying
	@Query("UPDATE Deposits d SET d.balance = ?2 , d.depositType = ?3 , d.estimatedBalance = ?4 , d.openingDate = ?5 , d.closingDate = ?6 WHERE d.depositId = ?1")
	public void updateDeposit(long depositId , double balance , String depositType , double estimatedBalance , Date openingDate , Date closingDate);
}

package com.mbank.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Deposits")
public class Deposits {
	
	@Id
	@GeneratedValue
	@Column(name="Deposit_Id")
	private Long depositId;
	
	@Column(name="Client_Id")
    private Long clientId;
	
	@Column(name="Balance")
	private double balance;
	
	@Column(name="Deposit_Type")
	private String depositType;
	
	@Column(name="Estimated_Balance")
	private double estimatedBalance;
	
	@Column(name="Opening_Date")
    Date openingDate;
	
	@Column(name="Closing_Date")
	Date closingDate;

	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public double getEstimatedBalance() {
		return estimatedBalance;
	}

	public void setEstimatedBalance(double estimatedBalance) {
		this.estimatedBalance = estimatedBalance;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

}

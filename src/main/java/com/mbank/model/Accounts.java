package com.mbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Accounts {
	
	@Id
	@GeneratedValue
	@Column(name="Account_Id")
	private Long accountId;
	
	@Column(name="Balance")
	private Double balance;
	
	@Column(name="Credit_Limit")
	private String creditLimit;
	
	@Column(name="Comment")
	private String comment;
	
	@GeneratedValue
	@Column(name="Client_Id" )
	private Long clientId;
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}

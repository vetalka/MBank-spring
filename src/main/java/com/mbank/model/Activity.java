package com.mbank.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Activity")
public class Activity  {
	
	@Id
	@GeneratedValue
	@Column(name="Activity_Id")
	private Long activityId;
	
	@Column(name="Amount")
	private Double amount;
	
	@Column(name="Activity_Date")
	Date activityDate;
	
	@Column(name="Commission")
	private double commission;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Client_Id")
	private Long clientId;
	

    public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}

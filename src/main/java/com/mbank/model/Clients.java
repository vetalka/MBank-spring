package com.mbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Clients")
public class Clients  {

	@Id
	@GeneratedValue
	@Column(name="Client_Id")
	private Long clientId;
	
	
	@Column(name="Client_Name")
	private String clientName;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Client_Type")
	private String clientType;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Comment")
	private String comment;
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

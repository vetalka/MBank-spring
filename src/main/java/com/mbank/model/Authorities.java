package com.mbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authorities")
public class Authorities {

	
	@Column(name="username")
	private String username;
	
	
	@Column(name="Authority")
	private String authority;

	@Id
	@GeneratedValue
	@Column(name="Id")
	private Long Id;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public String getClientName() {
		return username;
	}

	public void setClientName( String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}

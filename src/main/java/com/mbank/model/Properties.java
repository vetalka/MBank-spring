package com.mbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Properties")
public class Properties {
	
	@Id
	@Column(name="Prop_Key")
	private String propKey;
	
	@Column(name="Prop_Value")
	private String propValue;
	
	  

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}

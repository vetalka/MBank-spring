package com.mbank.service;

import java.util.List;

import com.mbank.model.Properties;

public interface PropertiesService {
	
	List<Properties> viewAllProperties();
	
	Properties getOneProperty(String propKey);
	
	void updateSystemProperty(String propKey , String propValue);

}

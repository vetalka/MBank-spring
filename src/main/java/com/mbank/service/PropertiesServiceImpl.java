package com.mbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbank.model.Properties;
import com.mbank.model.Users;
import com.mbank.repository.PropertiesRepository;
import com.mbank.repository.UsersRepository;

@Service("propertiesService")
public class PropertiesServiceImpl implements PropertiesService {
	
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	@Transactional
	public List<Properties> viewAllProperties() {
		
		return propertiesRepository.findAll();
	}

	@Transactional
	public Properties getOneProperty(String propKey) {
		
		return propertiesRepository.findOne(propKey);
	}

	@Transactional
	public void updateSystemProperty(String propKey, String propValue) {

		propertiesRepository.updateSystemProperty(propKey, propValue);
		
		Users user = usersRepository.findOne("admin");
		
		if(propKey.equals("admin_password")){
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(propValue);
			user.setPassword(hashedPassword);	
		}
	}
	
	

}

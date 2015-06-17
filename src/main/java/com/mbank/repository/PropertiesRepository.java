package com.mbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mbank.model.Properties;

public interface PropertiesRepository extends JpaRepository<Properties, String> {

	@Modifying
	@Query("UPDATE Properties p SET p.propValue = ?2 WHERE p.propKey = ?1")
	public void updateSystemProperty(String propKey , String propValue);
	
}

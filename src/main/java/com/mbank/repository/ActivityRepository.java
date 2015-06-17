package com.mbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbank.model.Activity;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	@Modifying
	@Query("UPDATE Activity a SET a.clientId = ?2 , a.amount = ?3 WHERE a.activityId = ?1")
	public void updateActivity(Long activityId , Long clientId , Double amount);
}

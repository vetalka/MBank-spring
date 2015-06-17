package com.mbank.service;

import java.util.List;

import com.mbank.model.Activity;

public interface ActivityService {

	void addActivity(Activity activity);
	
	Activity viewActivitiesDetails (Long activityId);

	List<Activity> getAllActivities();
	
	void depositToAccount(long clientId , double amount);
	
	void withdrawFromAccount(long clientId , double amount);
}

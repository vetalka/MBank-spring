package com.mbank.service;

import java.util.List;


import com.mbank.model.ClientType;
import com.mbank.model.Clients;

public interface ClientService {

	void addClient(Clients clients);

	List<ClientType> findAllClientType();
	
	void removeClient(Long clientId);
	
	void updateClientAdmin(Long clientId , String clientType , String comment);
	
	List<Clients> getAllClients();
	
	Clients viewClientDetails(Long clientId);
	
	void updateClient( long clientId, String Address, String Email, String Phone);
	
}

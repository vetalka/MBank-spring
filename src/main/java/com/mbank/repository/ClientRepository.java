package com.mbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbank.model.Clients;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Clients , Long> {

	@Modifying
	@Query("UPDATE Clients c SET c.clientType = ?2 , c.comment = ?3 WHERE c.clientId = ?1")
	public void updateClientAdmin( long clientId , String clientType , String comment);
	
	@Modifying
	@Query("UPDATE Clients c SET c.address = ?2 , c.email = ?3 , c.phone = ?4 WHERE c.clientId = ?1")
	public void updateClient( long clientId, String Address, String Email, String Phone);
	

}

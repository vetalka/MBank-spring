package com.mbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbank.model.Users;

@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, String>{

	@Modifying
	@Query("Delete Users where clientId=?1")
	public void delete(Long clientId);

//	@Modifying
//	@Query("Select Users s where s.username=?2 , s.password=?1")
//	public Users login(String username , String password);

	
}

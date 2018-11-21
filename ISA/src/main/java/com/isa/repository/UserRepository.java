package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	@Query("select u from User u where u.email = :email")
	public User findByEmail(@Param("email")String email);
	
}
